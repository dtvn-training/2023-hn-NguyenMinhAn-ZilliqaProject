package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.service.BlockService;
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

@Controller
public class OverviewController {

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

    @RequestMapping("/overview")
    public String home(Model model){
        model.addAttribute("title", "Overview Page");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
//        Long countBlock = blockService.count();
//        Long countTransaction = transactionService.count();
//        Long countException = exceptionService.count();
//        model.addAttribute("countBlock", countBlock);
//        model.addAttribute("countTransaction", countTransaction);
//        model.addAttribute("countException", countException);
        int pageNo = 0;
        Page<Blocks> blocks = blockService.getAllBlocks(pageNo);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Overview Page");
        model.addAttribute("size", blocks.getSize());
        model.addAttribute("totalPages", blocks.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("blocks", blocks);
        return "overview";
    }

    @GetMapping("/overview/{pageNo}")
    public String blocksPage(@PathVariable("pageNo") int pageNo, Model model){
        Page<Blocks> blocks = blockService.getAllBlocks(pageNo);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Overview Page");
        model.addAttribute("size", blocks.getSize());
        model.addAttribute("totalPages", blocks.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("blocks", blocks);
        return "overview";
    }
}
