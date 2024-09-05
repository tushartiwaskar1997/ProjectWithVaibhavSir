package com.example.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CommonSchedular {

    @Scheduled(cron = "0 * * * * * ")
    public void schedulejob(){
        System.out.println("job");
    }
}
