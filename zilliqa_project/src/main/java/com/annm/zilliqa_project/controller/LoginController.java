package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.web.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showLoginPage")
    public String loginForm(Model model){
        return "login";
    }

    @GetMapping("/showPage404")
    public String showPage404(Model model){
        return "404";
    }

    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model){
        RegisterUser ru = new RegisterUser();
        model.addAttribute("registerUser", ru);
        return "register";
    }
}
