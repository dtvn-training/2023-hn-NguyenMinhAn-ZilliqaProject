package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.config.ZilliqaBatchConfig;
import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.repository.ExceptionRepository;
import com.annm.zilliqa_project.repository.TransactionRepository;
import com.annm.zilliqa_project.service.RedisService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.batch.operations.JobRestartException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class BatchController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ZilliqaBatchConfig zilliqaBatchConfig;

    @Autowired
    ExceptionRepository exceptionRepository;

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    RedisService redisService;

//    @Autowired
//    @Qualifier("runJob1")
//    private Job runJob1;
//
//    @Autowired
//    @Qualifier("runJob2")
//    private Job runJob2;
//
//    @Autowired
//    @Qualifier("runJob3")
//    private Job runJob3;

//    public static void runJob(Job job) throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.afterPropertiesSet();
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//        try {
//            jobLauncher.run(job, jobParameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @GetMapping("/run-batch")
    public String goBatch() throws Exception {
        exceptionRepository.deleteAll();
        transactionRepository.deleteAll();
//        runJob(jobLauncherAsync, runJob1);
//        runJob(jobLauncherAsync, runJob2);
//        runJob(jobLauncherAsync, runJob3);
        zilliqaBatchConfig.runJob1();
        zilliqaBatchConfig.runJob2();
        zilliqaBatchConfig.runJob3();
        return "redirect:/home";
    }

//    @GetMapping("/delete-cache")
//    public String delete() throws Exception {
//        redisService.deleteAllData();
//        return "redirect:/user/home";
//    }
}
