package com.instagram.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.instagram.model.ImageTest;
import com.instagram.service.TestService;
import com.instagram.service.UserService;
import com.instagram.util.Constants;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	@Autowired
	UserService userService;
	@Autowired	ServletContext context;
	@GetMapping("/test/add")
	public String getAddCountryPage(Model model) {
		model.addAttribute("pageTitle", "Add Image");
		model.addAttribute("test", new ImageTest());
		model.addAttribute("message", "Add a new image");
		return "test/add";
	}

	@PostMapping("/test/add")
	public String add(@ModelAttribute(name = "test") ImageTest test,@RequestParam("file") MultipartFile file ,Model model,Authentication authentication) {
	//	var image_test = new ImageTest();
		if(test.getName() == null || test.getName().trim().isEmpty()) {
			throw new RuntimeException("Please give team name");
		}
				
		if (file.isEmpty()) {
			 throw new RuntimeException("Please select a file to upload");
		}
		 var userName = authentication.getName();
		 org.springframework.security.core.userdetails.User authenticateduser  = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     var username = authenticateduser.getUsername();
		 com.instagram.model.User user = userService.getUserByName(authenticateduser.getUsername());
		 var data = userService.getUserByUserName(userName).get();
         var UserId = data.getId();
		 try {
			 byte[] bytes = file.getBytes();
			 
			 String absoluteFilePath = Constants.UPLOADED_FOLDER;
			 
			 //File dir = Paths.get(absoluteFilePath + userName + "//").toFile();
			 File dir = Paths.get(absoluteFilePath).toFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String extension = "";
				StringTokenizer tokenizer = new StringTokenizer(file.getOriginalFilename(), ".");
				while (tokenizer.hasMoreTokens()) {
					extension = tokenizer.nextToken();
				}
				//System.currentTimeMillis();
				//String url = dir.getAbsolutePath() + "//" + userName + "." + extension;
				var millis = System.currentTimeMillis();
				String url = dir.getAbsolutePath() + "//" + millis + "." + extension;
				File serverFile = new File(url);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
	         test.setLogo("/img/" + millis + "." + extension);
	         test.setUserName(userName);
	         test.setUser(user);
	         test.setIsDelete(true);
	         testService.add(test);
	         
	 		model.addAttribute("message", "Image added successfully");
	 		return "redirect:/test/show-all";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }	

	}

	@RequestMapping(value = "test/show-all", params = {}, method = RequestMethod.GET)
	public String showAll(Model model,Authentication authentication) {
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
		model.addAttribute("totalPages", imagePage.getTotalPages());
		model.addAttribute("pageIndex", pageIndex);
		return "/test/show-all";
	}

	
}
