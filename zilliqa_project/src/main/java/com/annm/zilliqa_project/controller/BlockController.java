package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.service.BlockService;
import com.annm.zilliqa_project.service.serviceImpl.BlockServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.ExceptionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.TransactionServiceImpl;
import com.annm.zilliqa_project.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private RedisTemplate template;

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
        model.addAttribute("title", "Blocks Finding");
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
        model.addAttribute("keyword", keyword);
        return "block-results";
    }

    @GetMapping("/block-detail/{id}")
    public String blockDetails(@PathVariable("id") int id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Set<Integer> redisBlocks = template.opsForSet().members("blocks");
        Set<Integer> redisTransactions = template.opsForSet().members("transactions");
        Set<Integer> redisExceptions = template.opsForSet().members("exceptions");
        Blocks block = blockService.getByNumber(id);
        List<Transactions> transactions = transactionService.getByBlockNumber(id);
        List<Exceptions> exceptions = exceptionService.getByBlockNumber(id);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("title", "Block Details");
        model.addAttribute("blocks", block);
        model.addAttribute("transactions", transactions);
        model.addAttribute("exceptions", exceptions);
        model.addAttribute("redisBlocks", redisBlocks);
        model.addAttribute("redisTransactions", redisTransactions);
        model.addAttribute("redisExceptions", redisExceptions);
        return "block-details";
    }

    @GetMapping("/update-block/{id}")
    public String updateBlockForm(@PathVariable("id") int id, Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Blocks block = blockService.getByNumber(id);
        Long countBlock = blockService.count();
        Long countTransaction = transactionService.count();
        Long countException = exceptionService.count();
        model.addAttribute("countBlock", countBlock);
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("countException", countException);
        model.addAttribute("block", block);
        return "update-block";
    }

    @PostMapping("/update-block/{id}")
    public String processUpdate(@PathVariable("id") int id,
                                @ModelAttribute("block") Blocks block,
                                RedirectAttributes attributes){
        try{
            blockService.update(block);
            boolean exists = template.hasKey("blocks");

            if (exists) {
                template.opsForSet().add("blocks", id);
            } else {
                Set<Integer> blocks = new HashSet<>();
                blocks.add(id);
                template.opsForSet().add("blocks", blocks.toArray(new Integer[0]));
            }
            attributes.addFlashAttribute("success", "Update was successful");
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Update was not successful");
        }
        return "redirect:/user/block-detail/{id}";
    }
}
