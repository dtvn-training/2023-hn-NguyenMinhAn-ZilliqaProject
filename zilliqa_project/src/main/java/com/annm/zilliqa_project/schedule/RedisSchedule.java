package com.annm.zilliqa_project.schedule;

import com.annm.zilliqa_project.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisSchedule {

    @Autowired
    private RedisService redisService;

//    @Scheduled(cron = "0 * * ? * *")
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTaskWithCronExpression() {
        redisService.deleteAllData();
        System.out.println("Deleted!");
    }
}
