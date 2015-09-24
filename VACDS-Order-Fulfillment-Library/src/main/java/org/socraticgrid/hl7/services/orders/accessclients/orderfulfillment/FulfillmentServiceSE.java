package org.socraticgrid.hl7.services.orders.accessclients.orderfulfillment;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.socraticgrid.hl7.services.orders.interfaces.FulfillmentIFace;

/**
 * This class was Originally generated by Apache CXF 2.7.8
 * Modified by Jerry Goodnough
 * 
 */
@WebServiceClient(name = "FulfillmentServiceService", 
                  wsdlLocation = "classpath:Fulfillment.wsdl",
                  targetNamespace = "org.socraticgrid.hl7.services.orders.clients") 
public class FulfillmentServiceSE extends Service {

    public final static URL WSDL_LOCATION;

 

    public final static QName SERVICE = new QName("org.socraticgrid.hl7.services.orders.clients", "FulfillmentServiceService");
    public final static QName FulfillmentPort = new QName("org.socraticgrid.hl7.services.orders.clients", "FulfillmentPort");
    
    static {
        URL url = null;
        url = FulfillmentServiceSE.class.getClassLoader().getResource("wsdl/Fulfillment.wsdl");
        WSDL_LOCATION = url;
    }

    public FulfillmentServiceSE(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FulfillmentServiceSE(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FulfillmentServiceSE() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public FulfillmentServiceSE(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public FulfillmentServiceSE(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public FulfillmentServiceSE(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns FulfillmentSEI
     */
    @WebEndpoint(name = "fulfillmentPort")
    public FulfillmentIFace getFulfillmentPort() {
        return super.getPort(FulfillmentPort, FulfillmentIFace.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Fulfillment
     */
    @WebEndpoint(name = "FulfillmentPort")
    public FulfillmentIFace getFulfillmentPort(WebServiceFeature... features) {
        return super.getPort(FulfillmentPort, FulfillmentIFace.class, features);
    }

}
