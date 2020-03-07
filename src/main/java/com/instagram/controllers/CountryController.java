package com.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instagram.model.Country;
import com.instagram.service.CountryService;

@Controller
public class CountryController {
	@Autowired
	private CountryService countryService;

	@GetMapping("/country/add")
	public String addCountry_GET(Model model) {
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Please add a country");
		return "country/add";
	}

	@PostMapping("/country/add")
	public String addCountry(Model model, @ModelAttribute(name = "country") Country country) {
		countryService.add(country);
		model.addAttribute("message", "Country added successfully");
		return "redirect:/country/show-all";
	}

	@GetMapping("/country/show-all")
	public String showAll_GET(Model model) {
		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("message", "Showing all countries");
		return "country/show-all";
	}

	@GetMapping("/country/edit")
	public String edit_GET(@RequestParam("id") long id, Model model) {
		Country countryObj = countryService.getById(id);
		model.addAttribute("country", countryObj);
		model.addAttribute("message", "Edit the Country");
		return "country/edit";
	}

	@PostMapping("/country/edit")
	public String edit(Model model, @ModelAttribute(name = "country") Country country) {
		countryService.edit(country);
		model.addAttribute("message", "Country Edited Successfully");
		return "redirect:/country/show-all";
	}

	@GetMapping("/country/delete")
	public String delete_GET(Model model, @RequestParam("id") long id) {
		countryService.delete(id);
		model.addAttribute("message","Country deleted successfully");
		return "redirect:/country/show-all";
	}
}
