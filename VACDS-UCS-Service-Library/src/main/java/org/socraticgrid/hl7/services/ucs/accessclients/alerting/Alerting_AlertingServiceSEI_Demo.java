package org.socraticgrid.hl7.services.ucs.accessclients.alerting;

import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.AlertMessageHeader;
import org.socraticgrid.hl7.services.uc.model.AlertStatus;
import org.socraticgrid.hl7.services.uc.model.MessageBody;

public final class Alerting_AlertingServiceSEI_Demo {


	private Alerting_AlertingServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {

		String endpoint = "http://172.31.5.68:8080/UCS/alerting";

		AlertingServiceSE ss = new AlertingServiceSE();

		AlertingServiceSEI port = ss.getAlertingPort();
		
		((BindingProvider) port).getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		// Example subscribe Event Message
		{

			System.out.println("Invoking updateAlert...");

			AlertMessage alert = getBaseAlertMessage();
			alert.getHeader().setAlertStatus(AlertStatus.Acknowledged);
						
			boolean result= port.updateAlert(alert);
			
			System.out.println("updateAlert.result=" + result);

		}

		System.exit(0);
	}
	
	private static AlertMessage getBaseAlertMessage()
	{
		AlertMessage alert = new AlertMessage();
		AlertMessageHeader hdr = alert.getHeader();
		hdr.setAlertStatus(AlertStatus.New);
		hdr.setMessageId("A message id");
		MessageBody[] parts = new MessageBody[1];
		
		MessageBody body = new MessageBody();
		body.setContent("text");
		body.setContent("Test Data");
	
		parts[0] = body;
		
		alert.setParts(parts);
		return alert;
	}

}
