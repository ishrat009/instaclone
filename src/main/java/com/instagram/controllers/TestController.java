package com.instagram.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.instagram.util.Constants;

@Controller
public class TestController {
	@Autowired
	TestService testService;
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
		 try {
			 byte[] bytes = file.getBytes();
			 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
			 System.out.println(absoluteFilePath);
			 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
	         Files.write(path, bytes);
	         test.setLogo(file.getOriginalFilename());
	         var userName = authentication.getName();
	         test.setUserName(userName);
	         test.setIsDelete(true);
	         testService.add(test);
	         
	 		model.addAttribute("message", "Image added successfully");
	 		return "redirect:/test/show-all";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }	

	}
//	@RequestMapping(value = "test/show-all",params= {"_search","_pageIndex","_rows","_sort"},  method = RequestMethod.GET)
	@RequestMapping(value = "test/show-all",params= {},  method = RequestMethod.GET)
	public String showAll(Model model) {
//			@RequestParam(value = "_search") String searchText,
//			@RequestParam(value = "_pageIndex") int pageIndex,
//			@RequestParam(value = "_rows") int rows,
//			@RequestParam(value = "_sort") String sort) {

		 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
		// System.out.println(absoluteFilePath);
		// Path path = Paths.get(absoluteFilePath + getOriginalFilename());
		 String searchText="";
		 int pageIndex=0;
		 int rows=10;
		 String sort="NA";
		 var imagePage=testService.getAll(searchText,pageIndex,rows,sort);		 
		model.addAttribute("image_path", absoluteFilePath);
		model.addAttribute("pageTitle", "Image List");
		model.addAttribute("all_test",imagePage.getContent());
		model.addAttribute("message", "Showing all Image...");
		model.addAttribute("test", new ImageTest());
		model.addAttribute("totalPages",imagePage.getTotalPages());
		model.addAttribute("pageIndex",pageIndex);
		return "/test/show-all";
	}
	@RequestMapping(value = "test/post",params= {},  method = RequestMethod.GET)
	public String posts(Model model) {
//			@RequestParam(value = "_search") String searchText,
//			@RequestParam(value = "_pageIndex") int pageIndex,
//			@RequestParam(value = "_rows") int rows,
//			@RequestParam(value = "_sort") String sort) {

		 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
		// System.out.println(absoluteFilePath);
		// Path path = Paths.get(absoluteFilePath + getOriginalFilename());
		 String searchText="admin";
//		 String searchText="";
		 int pageIndex=0;
		 int rows=10;
		 String sort="NA";
		 var imagePage=testService.getAll(searchText,pageIndex,rows,sort);		 
		model.addAttribute("image_path", absoluteFilePath);
		model.addAttribute("pageTitle", "Image List");
		model.addAttribute("all_test",imagePage.getContent());
		model.addAttribute("message", "Showing all Image...");
		model.addAttribute("test", new ImageTest());
		model.addAttribute("totalPages",imagePage.getTotalPages());
		model.addAttribute("pageIndex",pageIndex);
		return "/test/show-all";
	}
	/*
	 * @GetMapping("/test/show-all") public String showAll_GET(Model model) {
	 * model.addAttribute("test", testService.getAll());
	 * model.addAttribute("message", "Showing all countries"); return
	 * "test/show-all"; }
	 */
	
}
