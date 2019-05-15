
package za.co.pps.auth.endpoints.tim;

import za.co.pps.auth.util.Config;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PPS_IDM_Service",
        //wsdlLocation = "https://dr-qaweb-01.ppsdmn.co.za:444/SecurityService/PPS_IDM_Service/services/PPS_IDM_ServicePort/wsdl/PPS_IDM_ServicePort.wsdl",
        targetNamespace = "http://PPS.co.za/PPS_IDM_Service/")
public class PPSIDMService
    extends Service
{

    private final static URL PPSIDMSERVICE_WSDL_LOCATION;
    private final static WebServiceException PPSIDMSERVICE_EXCEPTION;
    private final static QName PPSIDMSERVICE_QNAME = new QName("http://PPS.co.za/PPS_IDM_Service/", "PPS_IDM_Service");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Config.getIDMWSDL());
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PPSIDMSERVICE_WSDL_LOCATION = url;
        PPSIDMSERVICE_EXCEPTION = e;
    }

    public PPSIDMService() {
        super(__getWsdlLocation(), PPSIDMSERVICE_QNAME);
    }

    public PPSIDMService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PPSIDMSERVICE_QNAME, features);
    }

    public PPSIDMService(URL wsdlLocation) {
        super(wsdlLocation, PPSIDMSERVICE_QNAME);
    }

    public PPSIDMService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PPSIDMSERVICE_QNAME, features);
    }

    public PPSIDMService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PPSIDMService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PPSIDMServicePort
     */
    @WebEndpoint(name = "PPS_IDM_ServicePort")
    public PPSIDMServicePort getPPSIDMServicePort() {
        return super.getPort(new QName("http://PPS.co.za/PPS_IDM_Service/", "PPS_IDM_ServicePort"), PPSIDMServicePort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PPSIDMServicePort
     */
    @WebEndpoint(name = "PPS_IDM_ServicePort")
    public PPSIDMServicePort getPPSIDMServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://PPS.co.za/PPS_IDM_Service/", "PPS_IDM_ServicePort"), PPSIDMServicePort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PPSIDMSERVICE_EXCEPTION!= null) {
            throw PPSIDMSERVICE_EXCEPTION;
        }
        return PPSIDMSERVICE_WSDL_LOCATION;
    }

}