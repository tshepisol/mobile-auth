
package za.co.pps.auth.endpoints.tam;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.co.pps.auth.endpoints.tam package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.co.pps.auth.endpoints.tam
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TAMAuthenticationRq }
     * 
     */
    public TAMAuthenticationRq createTAMAuthenticationRq() {
        return new TAMAuthenticationRq();
    }

    /**
     * Create an instance of {@link SOAPFormatException }
     * 
     */
    public SOAPFormatException createSOAPFormatException() {
        return new SOAPFormatException();
    }

    /**
     * Create an instance of {@link TAMAuthenticationRs }
     * 
     */
    public TAMAuthenticationRs createTAMAuthenticationRs() {
        return new TAMAuthenticationRs();
    }

}
