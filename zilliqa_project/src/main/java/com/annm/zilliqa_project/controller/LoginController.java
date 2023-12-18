package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.dto.UserDto;
import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.service.serviceImpl.BlockServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.ExceptionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.TransactionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BlockServiceImpl blockService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private ExceptionServiceImpl exceptionService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title", "Login Page");
        return "login";
    }

    @RequestMapping("/overview")
    public String home(Model model){
        model.addAttribute("title", "Overview Page");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        return "overview";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewUser(@Valid @ModelAttribute("userDto")UserDto userDto,
                             BindingResult result,
                             Model model){
        try{
            if(result.hasErrors()){
                model.addAttribute("userDto", userDto);
                return "register";
            }
            String username = userDto.getUsername();
            Users user = userService.findByUsername(username);
            if (user != null) {
                model.addAttribute("userDto", userDto);
                model.addAttribute("usernameError", "Your username has been registered");
                return "register";
            }
            if (userDto.getPassword().equals(userDto.getRepeatPassword())){
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                userService.save(userDto);
                model.addAttribute("userDto", userDto);
                model.addAttribute("success", "Register successfully");
            } else {
                model.addAttribute("userDto", userDto);
                model.addAttribute("passwordError", "Passwords don't match!");
            }
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors", "Something went wrong. please try again later!");
        }
        return "register";
    }
}
