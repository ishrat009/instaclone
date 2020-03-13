package com.instagram.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instagram.dto.UserDto;
import com.instagram.enums.Role;
import com.instagram.model.ImageTest;
import com.instagram.model.User;
import com.instagram.repositories.UserRepository;
import com.instagram.service.TestService;
import com.instagram.service.UserService;
import com.instagram.util.Constants;

@Controller
public class RootController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public RootController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/")
	public String root(Model model,Authentication authentication) {
		var userName = authentication.getName();
		model.addAttribute("username", userName);
		return "all-posts";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		generateUsers();
		
		model.addAttribute("error", error);
		return "auth/login2";
	}
	@Autowired
	UserService userService;
	@Autowired
	TestService testService;
	@GetMapping("/index")
    public String getHomePage(Model model, Authentication authentication){
		var userName = authentication.getName();
		
	  String absoluteFilePath = Constants.UPLOADED_FOLDER;
		String searchText = "";
		int pageIndex = 0;
		int rows = 10;
		String sort = "NA";
		
		var imagePage = testService.getAll(searchText, pageIndex, rows, sort);
		model.addAttribute("image_path", absoluteFilePath);
		model.addAttribute("pageTitle", "Image List");
		model.addAttribute("all_post", imagePage.getContent());
		model.addAttribute("message", "Showing all Image...");
		model.addAttribute("post", new ImageTest());
		model.addAttribute("username", userName);
		model.addAttribute("totalPages", imagePage.getTotalPages());
		model.addAttribute("pageIndex", pageIndex);
        return "index2";
    }
	private void generateUsers() {
		if (userRepository.findByUsername("admin").isEmpty()) {
			var user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_ADMIN);
			user.setEmail("admin@gmail.com");
			user.setFullName("Insta Admin");
			userRepository.save(user);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			var user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_USER);
			user.setEmail("user@gmail.com");
			user.setFullName("Insta User");
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
		userService.addUser(userDto);
		 return "index2";
	}
	
}