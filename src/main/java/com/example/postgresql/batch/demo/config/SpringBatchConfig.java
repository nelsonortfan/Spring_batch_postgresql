package com.example.postgresql.batch.demo.config;

import com.example.postgresql.batch.demo.listener.FirstJobListener;
import com.example.postgresql.batch.demo.service.SecondTasklet;
import com.example.postgresql.batch.demo.task.SimpleTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private SimpleTask simpleTask;

    @Autowired
    private SecondTasklet secondTasklet;
    private final String SIMPLE_JOB = "Simple job of Nelson";
    private final String SIMPLE_TASK = "Simple task of mine";
    private final String SECOND_TASK = "Second simple task of mine";

    @Autowired
    private FirstJobListener firstJobListener;

    @Bean
    public Job simpleTaskJob(JobRepository jobRepository, Step simpleTaskStep, Step secondTaskStep){
        return new JobBuilder(SIMPLE_JOB,jobRepository)
                .start(simpleTaskStep)
                .next(secondTaskStep)
                .listener(firstJobListener)
                .build();
    }
    @Bean
    public Step simpleTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SIMPLE_TASK, jobRepository)
                .tasklet(simpleTask, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step secondTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SECOND_TASK, jobRepository)
                .tasklet(secondTasklet, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }

}
