package com.annm.zilliqa_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RedisExample implements CommandLineRunner {

    @Autowired
    private RedisTemplate template;

    @Override
    public void run(String... args) throws Exception {
//        SetOperations<String, String> setOperations = template.opsForSet();
//        Set<String> blocks = new HashSet<>();
//        blocks.add("1");
//        blocks.add("2");
//        blocks.add("3");
//        template.opsForSet().add("blocks", 4);
//        template.opsForSet().add("blocks", 5);
//        template.opsForSet().add("blocks", 6);
//        setOperations.add("setExample", blocks.toArray(new String[0]));
//        Set<String> printBlocks = template.opsForSet().members("setExample");
//        System.out.println(template.opsForSet().size("setExample"));
//        for (String block : printBlocks){
//            System.out.println("Element: " + block);
//        }

        Set<Integer> printBlocks = template.opsForSet().members("blocks");
        printBlocks.toArray();
        for (Integer block : printBlocks){
            System.out.println("Block: " + block);
        }

    }
}
