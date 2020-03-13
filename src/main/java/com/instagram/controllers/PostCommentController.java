package com.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.instagram.dto.PostCommentDto;
import com.instagram.model.PostComment;
import com.instagram.service.PostCommentService;
import com.instagram.service.TestService;
import com.instagram.service.UserService;

@Controller
public class PostCommentController {
	@Autowired
	TestService testService;
	@Autowired
	UserService userService;
	@Autowired
	PostCommentService postCommentService;

	@PostMapping("/post/addComment")
	public String addComment(@ModelAttribute(name = "postCommentDto") PostCommentDto postCommentDto, Model model,
			Authentication auth) {

		var username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		var data = userService.getUserByUserName(username).get();
		postCommentDto.setUserId(data.getId());
		testService.insertComment(postCommentDto);
		model.addAttribute("message", "Comment added successfully");
		return "index2";
	}


}
