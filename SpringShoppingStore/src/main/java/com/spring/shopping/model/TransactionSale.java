package com.spring.shopping.model;


                
public class TransactionSale
{
   private Integer amount;

   private String paymentreference;

   private String sessiontoken;

   private String orderid;

   private String purpose;

   private Auth auth;

   private String channelid;

   private String currency;

   private String apikey;

   public Integer getAmount ()
   {
       return amount;
   }

   public void setAmount (Integer amount)
   {
       this.amount = amount;
   }

   public String getPaymentreference ()
   {
       return paymentreference;
   }

   public void setPaymentreference (String paymentreference)
   {
       this.paymentreference = paymentreference;
   }

   public String getSessiontoken ()
   {
       return sessiontoken;
   }

   public void setSessiontoken (String sessiontoken)
   {
       this.sessiontoken = sessiontoken;
   }

   public String getOrderid ()
   {
       return orderid;
   }

   public void setOrderid (String orderid)
   {
       this.orderid = orderid;
   }

   public String getPurpose ()
   {
       return purpose;
   }

   public void setPurpose (String purpose)
   {
       this.purpose = purpose;
   }

   public Auth getAuth ()
   {
       return auth;
   }

   public void setAuth (Auth auth)
   {
       this.auth = auth;
   }

   public String getChannelid ()
   {
       return channelid;
   }

   public void setChannelid (String channelid)
   {
       this.channelid = channelid;
   }

   public String getCurrency ()
   {
       return currency;
   }

   public void setCurrency (String currency)
   {
       this.currency = currency;
   }

   public String getApikey ()
   {
       return apikey;
   }

   public void setApikey (String apikey)
   {
       this.apikey = apikey;
   }

   @Override
   public String toString()
   {
       return "ClassPojo [amount = "+amount+", paymentreference = "+paymentreference+", sessiontoken = "+sessiontoken+", orderid = "+orderid+", purpose = "+purpose+", auth = "+auth+", channelid = "+channelid+", currency = "+currency+", apikey = "+apikey+"]";
   }
}
			
			