package org.socraticgrid.hl7.services.eps.accessclients.subscription;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.socraticgrid.hl7.services.eps.interfaces.SubscriptionIFace;

/**
 * This class was Originally generated by Apache CXF 2.7.8
 * Modified by Jerry Goodnough
 * 
 */
@WebServiceClient(name = "SubscriptionServiceService", 
                  wsdlLocation = "classpath:wsdl/SubscriptionService.wsdl",
                  targetNamespace = "org.socraticgrid.hl7.services.eps") 
public class SubscriptionServiceSE extends Service {

    public final static URL WSDL_LOCATION;

 

    public final static QName SERVICE = new QName("org.socraticgrid.hl7.services.eps", "SubscriptionServiceService");
    public final static QName SubscriptionServicePort = new QName("org.socraticgrid.hl7.services.eps", "subscriptionServicePort");
    
    
    
    static {
        URL url = null;
        url = SubscriptionServiceSE.class.getClassLoader().getResource("wsdl/SubscriptionService.wsdl");
        WSDL_LOCATION = url;
    }

    public SubscriptionServiceSE(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SubscriptionServiceSE(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SubscriptionServiceSE() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubscriptionServiceSE(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubscriptionServiceSE(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubscriptionServiceSE(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SubscriptionServiceSEI
     */
    @WebEndpoint(name = "subscriptionServicePort")
    public SubscriptionIFace getSubscriptionPort() {
        return super.getPort(SubscriptionServicePort, SubscriptionIFace.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SubscriptionServiceSEI
     */
    @WebEndpoint(name = "subscriptionServicePort")
    public SubscriptionIFace getSubscriptionPort(WebServiceFeature... features) {
        return super.getPort(SubscriptionServicePort, SubscriptionIFace.class, features);
    }

}
