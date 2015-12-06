package com.spring.shopping.controller;

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
import com.spring.shopping.model.Order;
import com.spring.shopping.service.OrderService;

public class DbPullerJob {
	@Autowired
	private OrderService orderService;
	
    @Scheduled(fixedDelay=10000)
    public void updateEmployeeInventory(){
        System.out.println("Started fixed delay job");
        
        try {
        List<Order> orderlist = orderService.getAllPendingOrders();
        
        for(Order order : orderlist){
        	int status = getTransactionStatus(order.getTransactionid());
        	order.setOrderStatus(Order.mappStatus(status));
        	
        }
       
        }catch(Exception e){
        	int c;
        }
        
    }
    
    
    
    private int getTransactionStatus(long transactionid)throws Exception{
    	/*{
  "apikey": "79f68986-6f6b-40a0-98b6-417d302dec85",
  "auth": {
    "nonce": 21312312,
    "timestamp": "2015-11-21T01:51:17+00:01",
    "id": "transactionid",
    "authtoken": "f41d213a73f2de1fe88c63e518dd67d122d0077d75ee88b8d59007c1814fab7f"
  }
}*/
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
    
    
}
