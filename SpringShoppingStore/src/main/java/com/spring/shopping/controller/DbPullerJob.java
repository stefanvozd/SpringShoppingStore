package com.spring.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.spring.shopping.model.Order;
import com.spring.shopping.service.OrderService;

public class DbPullerJob {
	@Autowired
	private OrderService orderService;
	
    @Scheduled(fixedDelay=5000)
    public void updateEmployeeInventory(){
        System.out.println("Started fixed delay job");
        
        try {
        List<Order> orderlist = orderService.getAllPendingOrders();
        
        int x;
        x=1;
        x=3;
        
        }catch(Exception e){
        	int c;
        }
        
    }
}
