package za.co.pps.auth.endpoints.tam;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import za.co.pps.auth.domain.LoginCredentials;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class PPSTAMAuthServiceClientTest {

    PPSTAMAuthServiceClient ppstamAuthServiceClient;
    PPSTAMAuthServicePort mockPpstamAuthServicePort;

    Mockery mockery;

    @Before
    public void setup(){
        mockery = new Mockery(){{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};

        mockPpstamAuthServicePort = mockery.mock(PPSTAMAuthServicePort.class);

        ppstamAuthServiceClient = new PPSTAMAuthServiceClient();
        ppstamAuthServiceClient.setPpstamAuthServicePort(mockPpstamAuthServicePort);


    }

    @Test
    public void testAuthenticate() throws SOAPFormatException_Exception {
        LoginCredentials loginCredentials = getLoginCredentials("nkana", "Password1");

        TAMAuthenticationRq tamAuthentication = getTamAuthenticationRq(loginCredentials);
        TAMAuthenticationRs tamAuthenticationRs = getTamAuthenticationRs("R00");

        mockery.checking(new Expectations(){{
            oneOf(mockPpstamAuthServicePort).tamAuthentication(with(sameBeanAs(tamAuthentication)));
            will(returnValue(tamAuthenticationRs));
        }});
        TAMAuthenticationRs authenticate = ppstamAuthServiceClient.authenticate(loginCredentials);

        assertThat(authenticate, is(notNullValue()));
        assertThat(authenticate.getResultCode(), is("R00"));
    }


    @Test
    public void testAuthenticate_fail() throws SOAPFormatException_Exception {
        LoginCredentials loginCredentials = getLoginCredentials("nkana", "Password2");

        TAMAuthenticationRq tamAuthentication = getTamAuthenticationRq(loginCredentials);
        TAMAuthenticationRs tamAuthenticationRs = getTamAuthenticationRs("R01");


        mockery.checking(new Expectations(){{
            oneOf(mockPpstamAuthServicePort).tamAuthentication(with(sameBeanAs(tamAuthentication)));
            will(returnValue(tamAuthenticationRs));
        }});

        TAMAuthenticationRs authenticate = ppstamAuthServiceClient.authenticate(loginCredentials);

        assertThat(authenticate, is(notNullValue()));
        assertThat(authenticate.getResultCode(), is("R01"));
    }

    @Test
    public void testAuthenticate_NotFound() throws SOAPFormatException_Exception {
        LoginCredentials loginCredentials = getLoginCredentials("nkanaa", "Password2");

        TAMAuthenticationRq tamAuthentication = getTamAuthenticationRq(loginCredentials);
        TAMAuthenticationRs tamAuthenticationRs = getTamAuthenticationRs("R02");


        mockery.checking(new Expectations(){{
            oneOf(mockPpstamAuthServicePort).tamAuthentication(with(sameBeanAs(tamAuthentication)));
            will(returnValue(tamAuthenticationRs));
        }});


        TAMAuthenticationRs authenticate = ppstamAuthServiceClient.authenticate(loginCredentials);

        assertThat(authenticate, is(notNullValue()));
        assertThat(authenticate.getResultCode(), is("R02"));
    }

    private TAMAuthenticationRs getTamAuthenticationRs(String resultCode) {
        TAMAuthenticationRs tamAuthenticationRs = new TAMAuthenticationRs();
        tamAuthenticationRs.setResultCode(resultCode);
        return tamAuthenticationRs;
    }

    private TAMAuthenticationRq getTamAuthenticationRq(LoginCredentials loginCredentials) {
        TAMAuthenticationRq tamAuthentication = new TAMAuthenticationRq();
        tamAuthentication.setUsername(loginCredentials.getUsername());
        tamAuthentication.setPassword(loginCredentials.getPassword());
        return tamAuthentication;
    }

    private LoginCredentials getLoginCredentials(String username, String password) {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(username);
        loginCredentials.setPassword(password);
        return loginCredentials;
    }

}