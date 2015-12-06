package com.spring.shopping.controller;

import org.springframework.scheduling.annotation.Scheduled;

public class DbPullerJob {
 
    @Scheduled(fixedDelay=5000)
    public void updateEmployeeInventory(){
        System.out.println("Started fixed delay job");
        
    }
}
