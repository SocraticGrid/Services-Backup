package org.socraticgrid.hl7.services.uc.model;



public class SimpleMessage_test {

	public static void main(String... args){
		
		SimpleMessage simpleMessage = new SimpleMessage(System.currentTimeMillis()+"");
		
		//Watch - no ClassCastException
		@SuppressWarnings("unused")
		SimpleMessageHeader simpleMessageHeader = simpleMessage.getHeader();
	}
	
	
}
