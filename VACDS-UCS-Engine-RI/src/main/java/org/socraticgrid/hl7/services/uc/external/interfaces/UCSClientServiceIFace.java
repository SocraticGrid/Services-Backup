package org.socraticgrid.hl7.services.uc.external.interfaces;

import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.interfaces.UCSClientIntf;

@WebService(name = "ucsclient" 
, targetNamespace = "org.socraticgrid.hl7.services.uc.clients"
, serviceName = "ucsclient")
public interface UCSClientServiceIFace extends UCSClientIntf {

}
