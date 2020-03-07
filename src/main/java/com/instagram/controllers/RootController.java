package com.instagram.controllers;

import com.instagram.dto.UserDto;
import com.instagram.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instagram.enums.Role;
import com.instagram.model.User;
import com.instagram.repositories.UserRepository;

import java.time.LocalDateTime;

@Controller
public class RootController {

	@Autowired
	UserService userService;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public RootController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/")
	public String root() {
		return "index2";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		generateUsers();
		model.addAttribute("error", error);
		return "auth/login2";
	}

	private void generateUsers() {
		if (userRepository.findByUsername("admin").isEmpty()) {
			var user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			var user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_USER);
			userRepository.save(user);
		}
	}

	@GetMapping("/register")
	public String register(Model model, @RequestParam(name="error", required = false) String error) {
		User user = new User();
		model.addAttribute("user",user);
		return "auth/register";
	}



	@PostMapping("/register")
	public String register(@ModelAttribute("user")User user,Model model) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user,userDto);
		userDto.setPassword(passwordEncoder.encode(user.getPassword()));
//		userDto.setRole(Role.ROLE_USER);
		userService.addUser(userDto);

		return "redirect:/login2";
	}

} //End of Class