package com.annm.zilliqa_project.controller;

import com.annm.zilliqa_project.repository.ExceptionRepository;
import com.annm.zilliqa_project.repository.TransactionRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
    ExceptionRepository exceptionRepository;

    @Autowired
    @Qualifier("runJob1")
    private Job runJob1;

    @Autowired
    @Qualifier("runJob2")
    private Job runJob2;

    @Autowired
    @Qualifier("runJob3")
    private Job runJob3;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExecution execution;

    @Autowired
    private JobLauncher jobLauncherAsync;

    public static void runJob(JobLauncher jobLauncher, Job job) throws JobInstanceAlreadyCompleteException, NoSuchJobException, NoSuchJobExecutionException, JobParametersInvalidException, org.springframework.batch.core.repository.JobRestartException {
        JobExecution jobExecution = null;
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JobOperator jobOperator1 = null;
        jobOperator1.restart(jobExecution.getId());
    }

    public void reloadJob(long jobId) throws JobInstanceAlreadyCompleteException,
            NoSuchJobExecutionException,
            NoSuchJobException,
            JobRestartException,
            JobParametersInvalidException, org.springframework.batch.core.repository.JobRestartException {
        jobOperator.restart(jobId);
    }
    @GetMapping("/run-batch")
    public String goBatch(){
        exceptionRepository.deleteAll();
        transactionRepository.deleteAll();
        runJob(jobLauncherAsync, runJob1);
        runJob(jobLauncherAsync, runJob2);
        runJob(jobLauncherAsync, runJob3);
        return "redirect:/home";
    }
}
