package org.socraticgrid.hl7.services.ucs.accessclients.alerting;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.socraticgrid.hl7.services.ucs.accessclients.alerting.AlertingServiceSEI;

/**
 * This class was Originally generated by Apache CXF 2.7.8
 * Modified by Jerry Goodnough
 * 
 */
@WebServiceClient(name = "AlertingServiceService", 
                  wsdlLocation = "classpath:wsdl/AlertingService.wsdl",
                  targetNamespace = "org.socraticgrid.hl7.services.uc") 
public class AlertingServiceSE extends Service {

    public final static URL WSDL_LOCATION;

 

    public final static QName SERVICE = new QName("org.socraticgrid.hl7.services.uc", "AlertingServiceService");
    public final static QName AlertingServicePort = new QName("org.socraticgrid.hl7.services.uc", "alertingPort");
    
    
    
    static {
        URL url = null;
        url = AlertingServiceSE.class.getClassLoader().getResource("wsdl/AlertingService.wsdl");
        WSDL_LOCATION = url;
    }

    public AlertingServiceSE(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AlertingServiceSE(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AlertingServiceSE() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AlertingServiceSE(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AlertingServiceSE(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AlertingServiceSE(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns AlertingServiceSEI
     */
    @WebEndpoint(name = "alertingPort")
    public AlertingServiceSEI getAlertingPort() {
        return super.getPort(AlertingServicePort, AlertingServiceSEI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AlertingServiceSEI
     */
    @WebEndpoint(name = "alertingPort")
    public AlertingServiceSEI getAlertingPort(WebServiceFeature... features) {
        return super.getPort(AlertingServicePort, AlertingServiceSEI.class, features);
    }

}
