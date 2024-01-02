package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.service.BlockService;
import com.annm.zilliqa_project.service.serviceImpl.ExceptionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.TransactionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private ExceptionServiceImpl exceptionService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/account")
    public String home(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        int pageNo = 0;
        Page<Users> users = userService.getAllUsers(pageNo);
        model.addAttribute("title", "Home");
        model.addAttribute("size", users.getSize());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("users", users);
        return "account_administration";
    }

    @GetMapping("/account/{pageNo}")
    public String blocksPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Page<Users> users = userService.getAllUsers(pageNo);
        model.addAttribute("title", "Home");
        model.addAttribute("size", users.getSize());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("users", users);
        return "account_administration";
    }

    @PostMapping("/delete-account/{id}")
    public String deletedProduct(@PathVariable("id") Long id, RedirectAttributes attributes){
        try{
            Users user = userService.getById(id);
            user.getRoles().clear();
            userService.deleteById(id);
            attributes.addFlashAttribute("success", "Xóa thành công");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return "redirect:/user/account";
    }

    @GetMapping("/update-account/{id}")
    public String updateBlockForm(@PathVariable("id") Long id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Users user = userService.getById(id);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("user", user);
        return "update-account";
    }

    @PostMapping("/update-account/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("user") Users user,
                                RedirectAttributes attributes){
        try{
            userService.update(user);
            attributes.addFlashAttribute("success", "Update was successful");
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Update was not successful");
        }
        return "redirect:/user/account";
    }
}
