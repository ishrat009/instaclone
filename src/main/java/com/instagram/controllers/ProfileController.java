package com.instagram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.instagram.model.ImageTest;
import com.instagram.service.ProfileService;
import com.instagram.service.TestService;
import com.instagram.service.UserService;
import com.instagram.util.Constants;

@Controller
public class ProfileController {
	@Autowired
	ProfileService profileService;
	@Autowired
	UserService userService;
	@Autowired
	TestService testService;
	@GetMapping("/profile")
    public String getProfilePage(Model model, Authentication auth){
    	
    	String absoluteFilePath = Constants.UPLOADED_FOLDER;

    	var username =  auth.getName();
		
    	com.instagram.model.User user = userService.getUserByName(username);
    	System.out.println(user);
		List<ImageTest> posts = testService.getPostsById(user.getId());
		model.addAttribute("image_path", absoluteFilePath);		
        model.addAttribute("user",user);
        model.addAttribute("email",user.getEmail());
        model.addAttribute("fullName",user.getFullName());
        model.addAttribute("post_list",posts);
        model.addAttribute("post_size",posts.size());


        return "profile/profile-edit";
    }
	
}
