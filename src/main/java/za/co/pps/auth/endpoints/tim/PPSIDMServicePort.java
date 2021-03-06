
package za.co.pps.auth.endpoints.tim;

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
@WebService(name = "PPS_IDM_ServicePort", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PPSIDMServicePort {


    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tim.CreatePersonRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "CreatePerson", action = "http://PPS.co.za/PPS_IDM_Service/")
    @WebResult(name = "CreatePersonRs", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
    public CreatePersonRs createPerson(
        @WebParam(name = "CreatePersonRq", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
        CreatePersonRq parameters)
        throws SOAPFormatException_Exception
    ;

    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tim.GetPersonInfoRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "GetPersonInfo", action = "http://PPS.co.za/PPS_IDM_Service/")
    @WebResult(name = "GetPersonInfoRs", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
    public GetPersonInfoRs getPersonInfo(
        @WebParam(name = "GetPersonInfoRq", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
        GetPersonInfoRq parameters)
        throws SOAPFormatException_Exception
    ;

    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tim.GetIDMRequestStatusRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "GetIDMRequestStatus", action = "http://PPS.co.za/PPS_IDM_Service/")
    @WebResult(name = "GetIDMRequestStatusRs", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
    public GetIDMRequestStatusRs getIDMRequestStatus(
        @WebParam(name = "GetIDMRequestStatusRq", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
        GetIDMRequestStatusRq parameters)
        throws SOAPFormatException_Exception
    ;

    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tim.ChangePasswordRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "ChangePassword", action = "http://PPS.co.za/PPS_IDM_Service/")
    @WebResult(name = "ChangePasswordRs", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
    public ChangePasswordRs changePassword(
        @WebParam(name = "ChangePasswordRq", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
        ChangePasswordRq parameters)
        throws SOAPFormatException_Exception
    ;

    /**
     * 
     * @param parameters
     * @return
     *     returns za.co.pps.auth.endpoints.tim.ResetPasswordRs
     * @throws SOAPFormatException_Exception
     */
    @WebMethod(operationName = "ResetPassword", action = "http://PPS.co.za/PPS_IDM_Service/")
    @WebResult(name = "ResetPasswordRs", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
    public ResetPasswordRs resetPassword(
        @WebParam(name = "ResetPasswordRq", targetNamespace = "http://PPS.co.za/PPS_IDM_Service/", partName = "parameters")
        ResetPasswordRq parameters)
        throws SOAPFormatException_Exception
    ;

}
