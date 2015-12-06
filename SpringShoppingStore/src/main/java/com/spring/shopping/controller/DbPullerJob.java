package com.spring.shopping.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.spring.shopping.model.CreditCardForm;
import com.spring.shopping.model.Customer;
import com.spring.shopping.model.Order;
import com.spring.shopping.model.Product;
import com.spring.shopping.service.CustomerService;
import com.spring.shopping.service.OrderService;

public class DbPullerJob {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
    @Scheduled(fixedDelay=12000)
    public void updateEmployeeInventory(){
        System.out.println("Started fixed delay job");
        
        try {
        List<Order> orderlist = orderService.getAllPendingOrders();
        
        for(Order order : orderlist){
        	int status = getTransactionStatus(order.getTransactionid());
        	String mappStatus = Order.mappStatus(status);
			order.setOrderStatus(mappStatus);
			
			if (!mappStatus.equals("Pending")){
				orderService.updateOrder(order);
	        	
	        	if(mappStatus.equals("Paid")) {
	        		
	        		if (order.getSellerPhoneNumber() != null) {
	        			sendMoney(order.getSellerPhoneNumber(), order.getOrderTotal().subtract(new BigDecimal(2)).intValue() );
	        		}
	        		else {
		        		List<Product> allOrderItems = orderService.getAllOrderItems(order);
		        		for (Product product : allOrderItems) {
							Long customerId = product.getCustomerId();
							Customer customer = customerService.getCustomerById(customerId);
							String phoneNumber = customer.getPhoneNumber();
							sendMoney(phoneNumber, order.getOrderTotal().subtract(new BigDecimal(2)).intValue() );
						}
	        		}
	        	}
			}
        	
        }
       
        }catch(Exception e){
        	int c;
        }
        
    }
    
    
    
    private int getTransactionStatus(long transactionid)throws Exception{

    	int status = -1;
    	try {
    	System.setProperty("jsse.enableSNIExtension", "false");
    	Client client = ClientBuilder.newClient();
		JSONObject obj = new JSONObject();
		JSONObject auth = new JSONObject();
		obj.put("apikey", "aa10000005");
		auth.put("nonce", new Long(124134987));
		auth.put("timestamp", "2015-12-06T09:51:17+00:00");
		auth.put("id", ""+transactionid);
		//auth.put("authtoken", "01087a32b2eea0648d0fbf6e31ecb826903e83c3baa5b6349ea53c13a74ce0ac");
		obj.put("auth", auth);
		
		
		Entity payload = Entity.json(obj.toString());
		System.out.println(obj.toString());
		Response response = client.target("https://hackathon.halcom.com/MBillsWS/API/v1/transaction/status/"+transactionid)
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);
		//System.out.println("body:" + response.readEntity(String.class));
		//System.out.println("status: " + response.getStatus());
		//System.out.println("headers: " + response.getHeaders());
		
		String saleResponse = response.readEntity(String.class);

		JSONObject jsonSaleResponse = new JSONObject(saleResponse);
		status = (Integer) jsonSaleResponse.get("status");
		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return status;
    	
    }
    
    
    private void sendMoney(String phoneNumber, int money) throws Exception{
    	
    	String creditSessionToken = getCreditSessionToken(phoneNumber );
    	
//    	Thread.sleep(5000);
    	
    	Client client = ClientBuilder.newClient();
		JSONObject obj = new JSONObject();
		JSONObject auth = new JSONObject();
		obj.put("apikey", "aa10000005");
		obj.put("amount", money);
		obj.put("currency", "EUR");
		obj.put("sessiontoken", Long.parseLong(creditSessionToken));
		//obj.put("secretkey", "aa10000005");
		auth.put("nonce", new Long(124134987));
		auth.put("timestamp", "2015-12-06T09:51:17+00:00");
		auth.put("id", Long.parseLong(creditSessionToken));
		//auth.put("authtoken", "01087a32b2eea0648d0fbf6e31ecb826903e83c3baa5b6349ea53c13a74ce0ac");
		obj.put("auth", auth);
		
		Entity payload = Entity.json(obj.toString());
		System.out.println(obj.toString());
		Response response = client.target("https://hackathon.halcom.com/MBillsWS/API/v1/transaction/credit")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);

			
		String saleResponse = response.readEntity(String.class);
		System.out.println("body:" + saleResponse);
		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		

		JSONObject jsonSaleResponse = new JSONObject(saleResponse);
		String transactionId = (String) jsonSaleResponse.get("transactionid");
		int status = (Integer) jsonSaleResponse.get("status");
		
    }
    
    
	private String getCreditSessionToken(String phoneNumber){
		System.setProperty("jsse.enableSNIExtension", "false");
		
		
		Client client = ClientBuilder.newClient();
		Entity payload = Entity.json("{\"merchantid\":\"1144922459128400102\",\"phonenumber\":\""
				+ phoneNumber
				+ "\"}");
		Response response = client.target("https://hackathon.halcom.com/MBillsWS/API/v1/sessiontoken/credit")
				  .request(MediaType.APPLICATION_JSON_TYPE)
				  .post(payload);
				
				String sessionToken = response.readEntity(String.class);
				 
				return sessionToken.substring(
						sessionToken.indexOf(':') + 2,
						sessionToken.indexOf('}') - 1);
		
		 
	
	}
    
}
