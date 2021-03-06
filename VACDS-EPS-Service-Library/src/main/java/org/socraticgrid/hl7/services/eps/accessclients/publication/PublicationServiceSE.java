package org.socraticgrid.hl7.services.eps.accessclients.publication;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.socraticgrid.hl7.services.eps.interfaces.PublicationIFace;

/**
 * This class was Originally generated by Apache CXF 2.7.8
 * Modified by Jerry Goodnough
 * 
 */
@WebServiceClient(name = "PublicationServiceService", 
                  wsdlLocation = "classpath:wsdl/PublicationService.wsdl",
                  targetNamespace = "org.socraticgrid.hl7.services.eps") 
public class PublicationServiceSE extends Service {

    public final static URL WSDL_LOCATION;

 

    public final static QName SERVICE = new QName("org.socraticgrid.hl7.services.eps", "PublicationServiceService");
    public final static QName PublicationServicePort = new QName("org.socraticgrid.hl7.services.eps", "publicationPort");
    
    static {
        URL url = null;
        url = PublicationServiceSE.class.getClassLoader().getResource("wsdl/PublicationService.wsdl");
        WSDL_LOCATION = url;
    }

    public PublicationServiceSE(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PublicationServiceSE(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PublicationServiceSE() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PublicationServiceSE(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PublicationServiceSE(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PublicationServiceSE(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns TopicManagementServiceSEI
     */
    @WebEndpoint(name = "publicationPort")
    public PublicationIFace getPublicationPort() {
        return super.getPort(PublicationServicePort, PublicationIFace.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TopicManagementServiceSEI
     */
    @WebEndpoint(name = "publicationPort")
    public PublicationIFace getPublicationPort(WebServiceFeature... features) {
        return super.getPort(PublicationServicePort, PublicationIFace.class, features);
    }

}
