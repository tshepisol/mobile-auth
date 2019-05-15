
package za.co.pps.auth.endpoints.tam;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PPS_TAMAuth_ServicePort", targetNamespace = "http://PPS.co.za/PPS_TAMAuth_Service/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PPSTAMAuthServicePort {


    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tam.TAMAuthenticationRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "TAMAuthentication", action = "http://PPS.co.za/PPS_TAMAuth_Service/")
    @WebResult(name = "TAMAuthenticationRs", targetNamespace = "http://PPS.co.za/PPS_TAMAuth_Service/", partName = "parameters")
    public TAMAuthenticationRs tamAuthentication(
        @WebParam(name = "TAMAuthenticationRq", targetNamespace = "http://PPS.co.za/PPS_TAMAuth_Service/", partName = "parameters")
        TAMAuthenticationRq parameters)
        throws SOAPFormatException_Exception
    ;

}
