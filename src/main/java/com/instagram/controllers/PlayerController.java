package com.instagram.controllers;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.instagram.util.Util;
import com.instagram.dto.PlayerDto;
import com.instagram.request_models.Player;
import com.instagram.service.PlayerService;
import com.instagram.service.TeamService;

@Controller
public class PlayerController {
	@Autowired
	private PlayerService playerService;

	@Autowired
	private TeamService teamService;

	@GetMapping("/player/add")
	public String add_GET(Model model) {
		var roleList = new ArrayList<String>();
		roleList.add("Captain");
		roleList.add("Player");
		model.addAttribute("player", new Player());
		model.addAttribute("role_list", roleList);
		model.addAttribute("team_list", teamService.getAll());
		return "player/add";
	}
	
	@PostMapping("/player/add")
	public String add(Model model, @ModelAttribute("player") Player player) {
		
		System.out.println(player.toString());
		var team = teamService.getById(player.getTeamId());
		PlayerDto playerDto = new PlayerDto();
		if(player.getRole().equals("Captain")) {
			playerDto.setRole("ROLE_TEAM_CAPTAIN");
		}
		else {
			playerDto.setRole("ROLE_PLAYER");
		}
		playerDto.setActiveStatus(true);
		playerDto.setDob(Util.getFormattedDate(player.getDob(), Util.DOB_DATE_FORMAT));
		playerDto.setName(player.getName());
		playerDto.setAge(player.getAge());
		playerDto.setTeam(team);
		playerService.add(playerDto);
		model.addAttribute("message", "New Player Added Successfully");
		return "redirect:/player/show-all";
	}

	@GetMapping("/player/show-all")
	public String show_all(Model model) {
        var player_list = new ArrayList<Player>();
        playerService.getAll().forEach(playerDto -> {
            var player  = new Player();
            BeanUtils.copyProperties(playerDto,player);
            player.setName(playerDto.getName());
            player.setTeamName(playerDto.getTeam().getName());
            player.setRole(playerDto.getRole());
            player.setAge(playerDto.getAge());;
            player_list.add(player);

        });
        model.addAttribute("player", new Player());
        model.addAttribute("player_list",player_list);
        model.addAttribute("message","Showing All Employee"); 
        return "player/show-all";
		
	}

	@GetMapping("/player/edit")
	public String edit_GET(Model model, @RequestParam("id") long id) {
		 var playerDto = playerService.getById(id);
		 var player =  new Player();
		 BeanUtils.copyProperties(playerDto, player);
		 player.setTeamName(playerDto.getTeam().getName());
		 player.setTeamId(playerDto.getTeam().getId());
		 player.setName(playerDto.getName());
		 player.setAge(playerDto.getAge());
		 player.setRole(playerDto.getRole());
		 player.setDob(Util.getStringDate(playerDto.getDob(), Util.DOB_DATE_FORMAT));
		 
		 model.addAttribute("player", player);
		 model.addAttribute("team_list", teamService.getAll()); 
		 return "player/edit";
		 
	}

	@PostMapping("/player/edit")
	public String edit(Model model, @ModelAttribute(name = "player") Player player) {
		PlayerDto playerDto = playerService.getPlayerById(player.getId());
		playerDto.setName(player.getName());
		playerDto.setAge(player.getAge());
		playerDto.setDob(Util.getFormattedDate(player.getDob(), Util.DOB_DATE_FORMAT));
		playerDto.setActiveStatus(true);
		playerService.edit(playerDto);
		model.addAttribute("message", "Member Edited Successfully");
		return "redirect:/player/show-all";
	}
	@GetMapping("/player/deactive")
	public String delete_GET(Model model, @RequestParam("id") long id) {
		playerService.deactive(id);
		model.addAttribute("message","Player deleted successfully");
		return "redirect:/player/show-all";
	}
	
}