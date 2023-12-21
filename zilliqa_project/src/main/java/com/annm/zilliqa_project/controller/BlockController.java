package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.service.BlockService;
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
public class BlockController {

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

    @GetMapping("/block")
    public String block(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        List<Blocks> blocks = blockService.findTop10Blocks();
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("blocks", blocks);
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Search Results");
        return "block-finding";
    }

    @GetMapping("/search-blocks/{pageNo}")
    public String blockResult(Model model, @PathVariable("pageNo") int pageNo, @RequestParam(value = "keyword") String keyword, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Page<Blocks> blocks = blockService.searchBlocks(pageNo, keyword);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Search Results");
        model.addAttribute("size", blocks.getSize());
        model.addAttribute("totalPages", blocks.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("blocks", blocks);
        return "block-results";
    }
}
