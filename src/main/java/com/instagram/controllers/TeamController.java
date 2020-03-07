package com.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instagram.model.Team;
import com.instagram.service.CountryService;
import com.instagram.service.TeamService;

@Controller
public class TeamController {
	@Autowired
	private TeamService teamService;
	@Autowired
	private CountryService countryService;

	@GetMapping("/team/add")
	public String add_GET(Model model) {
		model.addAttribute("team", new Team());
		model.addAttribute("country_list", countryService.getAll());
		return "team/add";
	}

	@PostMapping("/team/add")
	public String add(Model model, @ModelAttribute("team") Team team) {
		teamService.add(team);
		model.addAttribute("message", "New Team Added Successfully");
		return "redirect:/team/show-all";
	}

	@GetMapping("/team/show-all")
	public String show_all(Model model) {
		model.addAttribute("team", new Team());
		model.addAttribute("team_list", teamService.getAll());
		model.addAttribute("message", "Showing All Team");
		return "team/show-all";
	}

	@GetMapping("/team/edit")
	public String edit_GET(Model model, @RequestParam("id") long id) {
		Team team = teamService.getById(id);
		model.addAttribute("team", team);
		model.addAttribute("country_list", countryService.getAll());
		return "team/edit";
	}

	@PostMapping("/team/edit")
	public String edit(Model model, @ModelAttribute(name = "team") Team team) {
		teamService.edit(team);
		model.addAttribute("message", "Team Edited Successfully");
		return "redirect:/team/show-all";
	}

	@GetMapping("/team/deactive")
	public String soft_delete_GET(Model model, @RequestParam("id") long id) {
		teamService.deactive(id);
		model.addAttribute("message", "Team Deactive successfully");
		return "redirect:/team/show-all";
	}

	@GetMapping(value = "/team/search")
	public @ResponseBody
	ResponseEntity<?> search(@RequestParam(name = "query") String query) {
	    var data = teamService.search(query);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
