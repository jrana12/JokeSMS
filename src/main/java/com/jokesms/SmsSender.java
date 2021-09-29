package com.jokesms;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
 

public class SmsSender {
	String mySms;
	
	SmsSender(String mySms){
		this.mySms=mySms;
		
	}
	

	public static final String ACCOUNT_SID = "[Your ACCOUNT_SID]"; 
    public static final String AUTH_TOKEN = "[Your AUTH_TOKEN]"; 
    
    public void sendSms(){
    	String[] numbers = {"Array","of","numbers"};
    	
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        for(String num:numbers) {
        	Message message = Message.creator( 
        
                new com.twilio.type.PhoneNumber(num),  
                "[Your Message_SID]", 
                mySms)      
            .create(); 
        	System.out.println("message sent to "+num); 
        }
        
    }
	
}
