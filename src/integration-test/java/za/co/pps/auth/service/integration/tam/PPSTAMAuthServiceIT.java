package za.co.pps.auth.service.integration.tam;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import za.co.pps.auth.endpoints.tam.*;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class PPSTAMAuthServiceIT {


    PPSTAMAuthServicePort ppstamAuthServicePort;

    @Before
    public void init(){
        ppstamAuthServicePort = new PPSTAMAuthService().getPPSTAMAuthServicePort();
    }


    @Test
    public void testAuthenticate() throws SOAPFormatException_Exception {

        TAMAuthenticationRq tamAuthenticationRq = new TAMAuthenticationRq();
        tamAuthenticationRq.setUsername("nkana");
        tamAuthenticationRq.setPassword("Password1");
        TAMAuthenticationRs tamAuthenticationRs = ppstamAuthServicePort.tamAuthentication(tamAuthenticationRq);

        assertThat(tamAuthenticationRs, is(notNullValue()));
        assertThat(tamAuthenticationRs.getResultCode(), is("R00"));
    }

}
