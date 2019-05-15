package za.co.pps.auth.service;

import org.jmock.Expectations;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.jmock.Mockery;
import org.junit.Test;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.domain.SecurityToken;
import za.co.pps.auth.endpoints.ppsi.*;
import za.co.pps.auth.endpoints.tam.PPSTAMAuthServiceClient;
import za.co.pps.auth.endpoints.tam.SOAPFormatException_Exception;
import za.co.pps.auth.endpoints.tam.TAMAuthenticationRs;
import za.co.pps.auth.endpoints.tim.GetPersonInfoRs;
import za.co.pps.auth.endpoints.tim.PPSIDMServiceClient;
import za.co.pps.auth.security.PPSJSONWebToken;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


public class AuthenticationServiceResourceTest {

    Mockery mockery;
    PPSTAMAuthServiceClient mockPpstamAuthServiceClient;
    PPSIDMServiceClient mockPpsidmServiceClient;
    PPSIServiceRESTClient mockPpsiServiceRESTClient;
    AuthenticationServiceResource authenticationServiceResource;
    PPSJSONWebToken ppsjsonWebToken;
    private Response mockResponse;

    @Before
    public void setUp() throws Exception {
        authenticationServiceResource = new AuthenticationServiceResource();
        authenticationServiceResource.init();

        ppsjsonWebToken = new PPSJSONWebToken();
        ppsjsonWebToken.setCryptoIssuer("PPS");
        ppsjsonWebToken.setCryptoSecret("password");
        ppsjsonWebToken.setExpire("1");
        ppsjsonWebToken.setSchema("SCHEMA");
        ppsjsonWebToken.setRole("ROLE");
        ppsjsonWebToken.setAudience("GUID");

        mockery = new Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};
        mockPpstamAuthServiceClient = mockery.mock(PPSTAMAuthServiceClient.class);
        mockPpsidmServiceClient = mockery.mock(PPSIDMServiceClient.class);

        mockResponse = mockery.mock(Response.class);

        mockPpsiServiceRESTClient = mockery.mock(PPSIServiceRESTClient.class);
        authenticationServiceResource.ppstamAuthServiceClient = mockPpstamAuthServiceClient;
        authenticationServiceResource.ppsidmServiceClient = mockPpsidmServiceClient;

        authenticationServiceResource.ppsiServiceRESTClient = mockPpsiServiceRESTClient;
        authenticationServiceResource.ppsjsonWebToken = ppsjsonWebToken;
    }

    @Test
    public void testPPS_Authenticate_success() throws SOAPFormatException_Exception, za.co.pps.auth.endpoints.tim.SOAPFormatException_Exception {


        mockery.checking(new Expectations(){{
            oneOf(mockPpstamAuthServiceClient).authenticate(with(sameBeanAs(getLoginCredentials("test1", "password", "IMEI"))));
            will(returnValue(getTamAuthenticationRs("R00")));

            oneOf(mockPpsidmServiceClient).getPersonalInfo("test1");
            will(returnValue(getPersonalInfo()));

            oneOf(mockPpsiServiceRESTClient).validate(with(sameBeanAs(getClientValidateRequest())) );
            will(returnValue(mockResponse));

            oneOf(mockResponse).readEntity(ClientValidationResponse.class);
            will(returnValue(getValidateResponse()));

        }});

        Response authenticateResponse = authenticationServiceResource.authenticate("1.0", getLoginCredentials("test1", "password", "IMEI"));

        assertThat(authenticateResponse, is(notNullValue()));

        assertThat(authenticateResponse.getStatus(), is(200));
        assertThat(authenticateResponse.hasEntity(), is(true));
    }

    @Test
    public void testPPSI_Authenticate_success() throws SOAPFormatException_Exception, za.co.pps.auth.endpoints.tim.SOAPFormatException_Exception {

        mockery.checking(new Expectations(){{
            oneOf(mockPpstamAuthServiceClient).authenticate(with(sameBeanAs(getLoginCredentials("test2", "password", "IMEI"))));
            will(returnValue(getTamAuthenticationRs("R02")));
            oneOf(mockPpsiServiceRESTClient).authenticate(with(sameBeanAs(getLoginCredentials("test2", "password", "IMEI"))));
            will(returnValue(mockResponse));

            oneOf(mockResponse).getStatus();
            will(returnValue(200));

            oneOf(mockResponse).readEntity(ClientAuthenticateResponse.class);
            will(returnValue(getAuthenticationResponse()));

        }});

        Response authenticateResponse = authenticationServiceResource.authenticate("1.0", getLoginCredentials("test2", "password", "IMEI"));

        assertThat(authenticateResponse, is(notNullValue()));

        assertThat(authenticateResponse.getStatus(), is(200));
        assertThat(authenticateResponse.hasEntity(), is(true));

    }



    @Test
    public void testPPS_Validation_missing_IMEI() throws SOAPFormatException_Exception, za.co.pps.auth.endpoints.tim.SOAPFormatException_Exception {

        Response authenticateResponse = authenticationServiceResource.authenticate("1.0", getLoginCredentials("test1", "password", null));

        assertThat(authenticateResponse, is(notNullValue()));

        assertThat(authenticateResponse.getStatus(), is(400));
        assertThat(authenticateResponse.hasEntity(), is(true));

    }


    @Test
    public void testPPS_Validate() throws SOAPFormatException_Exception, za.co.pps.auth.endpoints.tim.SOAPFormatException_Exception, UnsupportedEncodingException {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJfaWQiOiI0MzIxIiwic3ViIjoidGxldHN3IiwiaHR0cDovL3BwcyI6IiIsImlkX251bWJlciI6IjgwMDgwOCIsImltZWlfc3RhdHVzIjoiT0siLCJyb2xlIjoiUFBTIFJvbGUiLCJpc3MiOiJ1c2VyIiwiZ2l2ZW5fbmFtZSI6IlRzaGVwaXNvIiwiYXVkIjoiR1VJRCIsInVuaXF1ZV9uYW1lIjoidGxldHN3IiwibmJmIjoxNTA2OTI2MTMxLCJpbWVpIjoiSU1FSSIsImlkX3R5cGUiOiJJRCIsImV4cCI6MTUwNjkyNjE5MSwiZmFtaWx5X25hbWUiOiJMZXRzd2FsbyJ9.CCH-agY7Q8ZJtL6NioCO25GcoFb5R9owmPt0PYzur6k";
        SecurityToken securityToken = new SecurityToken();
        securityToken.setUsername("USER");
        securityToken.setjWTTokenRaw(token);



        ppsjsonWebToken = mockery.mock(PPSJSONWebToken.class);
        authenticationServiceResource.setPpsjsonWebToken(ppsjsonWebToken);

        mockery.checking( new Expectations(){{
            oneOf(ppsjsonWebToken).validateToken("USER", securityToken.getjWTTokenRaw());

            oneOf(mockResponse).getStatus();
            will(returnValue(200));
        }});


        Response authenticateResponse = authenticationServiceResource.validate("1.0", securityToken);

        assertThat(authenticateResponse, is(notNullValue()));

        assertThat(authenticateResponse.getStatus(), is(200));

    }




    private GetPersonInfoRs getPersonalInfo() {
        GetPersonInfoRs getPersonInfoRs = new GetPersonInfoRs();
        getPersonInfoRs.setUsername("test1");
        getPersonInfoRs.setMemberNo("1234");
        getPersonInfoRs.setIdNumber("800101");

        return  getPersonInfoRs;
    }

    private LoginCredentials getLoginCredentials(String username, String password, String IMEI) {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(username);
        loginCredentials.setPassword(password);
        loginCredentials.setIMEI(IMEI);
        return loginCredentials;
    }

    private TAMAuthenticationRs getTamAuthenticationRs(String resultCode) {
        TAMAuthenticationRs tamAuthenticationRs = new TAMAuthenticationRs();
        tamAuthenticationRs.setResultCode(resultCode);
        return tamAuthenticationRs;
    }

    public ClientValidationResponse getValidateResponse() {
        ClientValidationResponse clientValidationResponse = new ClientValidationResponse();
        clientValidationResponse.setId("990909");
        clientValidationResponse.setPpsmember("4321");

        return clientValidationResponse;
    }

    public ClientAuthenticateResponse getAuthenticationResponse() {
        ClientAuthenticateResponse clientAuthenticateResponse = new ClientAuthenticateResponse();
        clientAuthenticateResponse.setIno("990909");
        clientAuthenticateResponse.setPpsmember("4321");

        return clientAuthenticateResponse;
    }

    public ClientValidateRequest getClientValidateRequest() {
        ClientValidateRequest clientValidateRequest = new ClientValidateRequest("1234","800101");

        return clientValidateRequest;
    }
}