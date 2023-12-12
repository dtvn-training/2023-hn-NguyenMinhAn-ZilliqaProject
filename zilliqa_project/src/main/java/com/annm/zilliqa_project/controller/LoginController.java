package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Roles;
import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.repository.RoleRepository;
import com.annm.zilliqa_project.service.UserService;
import com.annm.zilliqa_project.web.RegisterUser;
import com.google.errorprone.annotations.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/login")
public class LoginController {

    UserService userService;
    RoleRepository roleRepository;

    @Autowired
    public LoginController(UserService userService, RoleRepository repository) {
        this.userService = userService;
        this.roleRepository = repository;
    }

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

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") RegisterUser registerUser,
                         BindingResult result,
                         Model model,
                         HttpSession session){
        String username = registerUser.getUsername();

        if (result.hasErrors()){
            return "register";
        }
        Users userExisting = userService.findByUsername(username);
        if (userExisting != null){
            model.addAttribute("registerUser",new RegisterUser());
            model.addAttribute("my_error", "Account has been existed");
            return "register";
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Users user = new Users();
        user.setUsername(registerUser.getUsername());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());

        Roles defaultRole = roleRepository.findByName("ROLE_USER");
        Collection<Roles> roles = new ArrayList<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        userService.save(user);

        // confirm success
        session.setAttribute("myuser", user);

        return "register-confirmation";
    }

    @GetMapping("/addAdmin")
    public void addAdmin(Model model){
        Roles roles = new Roles();
        roles.setId(1L);
        roles.setName("ROLE_ADMIN");
        roleRepository.save(roles);
    }
}
