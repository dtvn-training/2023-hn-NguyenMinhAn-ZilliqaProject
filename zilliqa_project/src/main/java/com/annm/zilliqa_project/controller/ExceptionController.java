package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.service.serviceImpl.BlockServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.ExceptionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.TransactionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class ExceptionController {
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

    @GetMapping("/update-exception-b/{id}")
    public String updateTransactionFormB(@PathVariable("id") int id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Exceptions exception = exceptionService.getById(id);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("exception", exception);
        return "update-exception-b";
    }

    @GetMapping("/update-exception-t/{id}/{t_id}")
    public String updateTransactionFormT(@PathVariable("id") int id, @PathVariable("t_id") int t_id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Transactions transaction = transactionService.getById(t_id);
        Exceptions exception = exceptionService.getById(id);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("exception", exception);
        model.addAttribute("transaction", transaction);
        return "update-exception-t";
    }

    @PostMapping("/update-exception-b/{id}")
    public String processUpdateB(@PathVariable("id") int id,
                                 @ModelAttribute("exception") Exceptions exception,
                                 RedirectAttributes attributes){
        try{
            exceptionService.update(exception);
            attributes.addFlashAttribute("success", "Update was successful");
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Update was not successful");
        }
        return "redirect:/user/block-detail/{id}";
    }

    @PostMapping("/update-exception-t/{id}/{t_id}")
    public String processUpdateT(@PathVariable("id") int id,
                                 @PathVariable("t_id") int t_id,
                                 @ModelAttribute("exception") Exceptions exception,
                                 RedirectAttributes attributes){
        try{
            exceptionService.update(exception);
            attributes.addFlashAttribute("success", "Update was successful");
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Update was not successful");
        }
        return "redirect:/user/transaction-detail/{t_id}";
    }
}
