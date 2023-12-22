package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.service.serviceImpl.BlockServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.ExceptionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.TransactionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class TransactionController {
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

    @GetMapping("/transaction")
    public String transaction(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        List<Transactions> transactions = transactionService.findTop10Transactions();
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("transactions", transactions);
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Transactions Finding");
        return "transaction-finding";
    }

    @GetMapping("/search-transactions/{pageNo}")
    public String blockResult(Model model, @PathVariable("pageNo") int pageNo, @RequestParam(value = "keyword") String keyword, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Page<Transactions> transactions = transactionService.searchTransactions(pageNo, keyword);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Search Results");
        model.addAttribute("size", transactions.getSize());
        model.addAttribute("totalPages", transactions.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("transactions", transactions);
        model.addAttribute("keyword", keyword);
        return "transaction-results";
    }

    @GetMapping("/transaction-detail/{id}")
    public String transactionDetails(@PathVariable("id") int id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Transactions transactions = transactionService.getById(id);
        String transactionId = transactions.getTransactionId();
        List<Exceptions> exceptions = exceptionService.getByTransactionId(transactionId);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Transaction Details");
        model.addAttribute("transactions", transactions);
        model.addAttribute("exceptions", exceptions);
        return "transaction-details";
    }
}
