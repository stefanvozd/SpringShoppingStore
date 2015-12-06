package com.spring.shopping.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.spring.shopping.controller.constants.ControllerConstants;
import com.spring.shopping.model.AddressForm;
import com.spring.shopping.model.Auth;
import com.spring.shopping.model.CreditCardForm;
import com.spring.shopping.model.Customer;
import com.spring.shopping.model.Order;
import com.spring.shopping.model.OrderItemExtended;
import com.spring.shopping.model.Product;
import com.spring.shopping.model.TransactionSale;
import com.spring.shopping.service.CartData;
import com.spring.shopping.service.CartService;
import com.spring.shopping.service.MailSenderService;
import com.spring.shopping.service.OrderService;
import com.spring.shopping.service.PaymentService;
import com.spring.shopping.util.SessionUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Controller
public class OrderController {

//	2015-11-21T01:51:17+00:00
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss+00:00");
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CartService cartService;
	// @Autowired
	// private ProductConfigService productConfigService;
	@Autowired
	private MailSenderService mailSenderService;
	@SuppressWarnings("unused")
	private HttpSession session;

	private String getSessionToken(CreditCardForm creditCardForm, int amount){
		System.setProperty("jsse.enableSNIExtension", "false");
		
		Client client = ClientBuilder.newClient();
		Entity payload = Entity.json("{\"merchantid\":\"1144922459128400102\",\"amount\":1000,\"currency\":\"EUR\",\"phonenumber\":\""
				+ creditCardForm.getCreditCardNumber()
				+ "\",\"securitycode\":\""
				+ creditCardForm.getName() + "\"}");
		Response response = client.target("https://hackathon.halcom.com/MBillsWS/API/v1/sessiontoken/phonenumber")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);
		
		String sessionToken = response.readEntity(String.class);
		 
		return sessionToken.substring(
				sessionToken.indexOf(':') + 2,
				sessionToken.indexOf('}') - 1);
	}
	
	@RequestMapping(value = "/createOrderByCC", method = RequestMethod.POST)
	public String createOrder(Model model,
			@ModelAttribute("paymentForm") CreditCardForm creditCardForm,
			HttpServletRequest request, HttpServletResponse response2)
			throws ParseException, IOException {
		
		//String amm = request.getParameter("amount").split("\\.")[0];
		//int amount = Integer.parseInt(amm);
		int amount=3000;
		String sessionToken = getSessionToken(creditCardForm, amount);
		
		Client client = ClientBuilder.newClient();
		JSONObject obj = new JSONObject();
		JSONObject auth = new JSONObject();
		obj.put("apikey", "aa10000005");
		obj.put("amount", amount);
		obj.put("currency", "EUR");
		obj.put("sessiontoken", Long.parseLong(sessionToken));
		obj.put("purpose", "uCodeiSell Payment");
		//obj.put("paymentreference", "aa10000006");
		obj.put("orderid", "aa10000006");
		//obj.put("secretkey", "aa10000005");
		auth.put("nonce", new Long(124134987));
		auth.put("timestamp", "2015-12-06T09:51:17+00:00");
		auth.put("id", Long.parseLong(sessionToken));
		//auth.put("authtoken", "01087a32b2eea0648d0fbf6e31ecb826903e83c3baa5b6349ea53c13a74ce0ac");
		obj.put("auth", auth);

		
		Entity payload = Entity.json(obj.toString());
		System.out.println(obj.toString());
		Response response = client.target("https://hackathon.halcom.com/MBillsWS/API/v1/transaction/sale")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);

		
		System.out.println("body:" + response.readEntity(String.class));
		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		session = SessionUtils.createSession(request);
		// Retrieve Details about the Cart,Customer and Address Details
		// used to create detailed Order
		Customer customer = SessionUtils.getSessionVariables(request,
				"customer");
		//AddressForm address = SessionUtils.getSessionVariables(request,"address");
		Order order=null;
		if(customer!=null)
		{
			order = orderService.createOrder(cartService, customer, null,request);
			payAmountByCreditCard(creditCardForm, request);
	
			SessionUtils.setSessionVariables(order, request, "orderDetails");
			List<Product> productsList = orderService.getAllOrderItems(order);
			StringBuffer sb = new StringBuffer();
			sb.append("Hello " + customer.getUserName() + "\n");
			sb.append("Thank you for shopping at eShopper.Happy Shopping!!\n");
			sb.append("OrderId-" + order.getOrderId() + "/n");
			sb.append("Products-/n");
			for (Product p : productsList) {
				sb.append(p.getName() + "  Rs." + p.getPrice() + "\n");
			}
			sb.append("Your Order Status is: " + order.getOrderStatus());
			sb.append("You will get further notification.Once your order is processed");
			mailSenderService.sendEmail(customer.getEmailAddress(),
					customer.getUserName(), sb.toString(),
					"Order Confirmation for " + customer.getUserName());
				return "redirect:/order";
		}
		else
			return "successPayment";
	}

	public void payAmountByCreditCard(CreditCardForm creditCardForm,
			HttpServletRequest request) throws IOException {

		creditCardForm = paymentService.gatherCardDetails(creditCardForm,
				request);
		paymentService.payByCreditCard(creditCardForm);
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String getOrderConfirmPage(Model model, HttpServletRequest request) {
		Order order = SessionUtils.getSessionVariables(request, "orderDetails");
		model.addAttribute("orderDetails", order);
		List<Product> productsList = orderService.getAllOrderItems(order);
		model.addAttribute("prodList", productsList);
		CartData cartData = SessionUtils.getSessionVariables(request,
				ControllerConstants.CART);
		cartService.clearCart(cartData);
			return "order";
	}	
	
	@RequestMapping(value = "/sellHistory", method = RequestMethod.GET)
	public String getSellHistory(Model model, HttpServletRequest request) {
		Long productId = Long.valueOf(request.getParameter( "productId"));
		
		List<OrderItemExtended> orderItemExtendedList = orderService.getAllOrdersForProduct(productId);
		model.addAttribute("orderList", orderItemExtendedList);
		model.addAttribute("page", "ordersHistoryList");
		return "account";
		
	}

	
}
