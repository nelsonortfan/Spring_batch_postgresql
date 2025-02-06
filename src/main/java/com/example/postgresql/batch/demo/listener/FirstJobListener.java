package com.example.postgresql.batch.demo.listener;

import com.example.postgresql.batch.demo.domain.Control;
import com.example.postgresql.batch.demo.repository.ControlRepository;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before the job using my listener Nelson");
        System.out.println("The name of the job is " + jobExecution.getJobInstance().getJobName());
        Control control = controlRepository.findTopByOrderByIdDesc();
        if(control.isStatus()){
            System.out.println("The value of the status of the last job was " + control.isStatus() + "  and the job ran");
        }
        else{
            System.out.println("The job didn't run so today it can run");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After the job using my listener Nelson");
        Control control = new Control();
        control.setNameJob(jobExecution.getJobInstance().getJobName());
        //control.setStatus(true);
        control.setStatus(false);
        controlRepository.save(control);
    }

}
