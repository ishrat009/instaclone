package com.instagram.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String show_profile(Model model, Authentication authentication) {
        var userName = authentication.getName();
        model.addAttribute("username", userName);
        model.addAttribute("userNameId", userName);
        return "profile/profile";
    }

    @GetMapping("/profile/id")
    public String show_profile(@RequestParam("userNameId") String userNameId, Model model, Authentication authentication) {
        var userName = authentication.getName();
        model.addAttribute("username", userName);
        model.addAttribute("userNameId", userNameId);
        return "profile/profile";
    }

} //End of Class
