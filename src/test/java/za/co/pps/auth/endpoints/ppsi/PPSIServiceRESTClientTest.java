package za.co.pps.auth.endpoints.ppsi;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.weld.WeldJUnit4Runner;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class PPSIServiceRESTClientTest {

    PPSIServiceRESTClient ppsiServiceRESTClient;

    Mockery mockery;
    Client mockClient;
    Response mockResponse;
    WebTarget mockWebTarget;
    Invocation.Builder mockBuilder;


    @Before
    public void setup(){
        mockery = new Mockery(){{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};

        ppsiServiceRESTClient = new PPSIServiceRESTClient();

        mockClient = mockery.mock(Client.class);
        mockResponse = mockery.mock(Response.class);
        mockWebTarget = mockery.mock(WebTarget.class);
        mockBuilder = mockery.mock(Invocation.Builder.class);

        ppsiServiceRESTClient.setPpsiURL("http://ppsiURL");
        ppsiServiceRESTClient.setClient(mockClient);
    }


    @Test
    public void testAuthenticate(){
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername("swartt2");
        loginCredentials.setPassword("D3vt3sting#");
        loginCredentials.setIMEI("IMEI");
        loginCredentials.setClientID("");
        loginCredentials.setAuthenticationSource("");
        loginCredentials.setPublicEncryptionKey("");

        mockery.checking(new Expectations(){{
            oneOf(mockClient).target("http://ppsiURL");
            will(returnValue(mockWebTarget));

            oneOf(mockWebTarget).path("/authenticate");
            will(returnValue(mockWebTarget));

            oneOf(mockWebTarget).request(MediaType.APPLICATION_JSON);
            will(returnValue(mockBuilder));

            oneOf(mockBuilder).post(with(sameBeanAs(Entity.entity(new AuthenticationRequest(loginCredentials.getUsername(), loginCredentials.getPassword()), MediaType.APPLICATION_JSON))));
            will(returnValue(mockResponse));

            oneOf(mockResponse).getStatus();
            will(returnValue(200));

            oneOf(mockResponse).readEntity(ClientAuthenticateResponse.class);
            will(returnValue(new ClientAuthenticateResponse()));
        }});

        Response ppsiResponse = ppsiServiceRESTClient.authenticate(loginCredentials);

        assertThat(ppsiResponse.getStatus(), is(200));

        ClientAuthenticateResponse clientAuthenticateResponse = ppsiResponse.readEntity(ClientAuthenticateResponse.class);
        System.out.println(clientAuthenticateResponse);
        assertThat(clientAuthenticateResponse, is(notNullValue()));

    }

    @Test
    public void validate(){
        ClientValidateRequest clientValidateRequest =new ClientValidateRequest();
        clientValidateRequest.setId("7806145045087");
        clientValidateRequest.setPpsmember("124328792");


        mockery.checking(new Expectations(){{
            oneOf(mockClient).target("http://ppsiURL");
            will(returnValue(mockWebTarget));

            oneOf(mockWebTarget).path("/client/validate");
            will(returnValue(mockWebTarget));

            oneOf(mockWebTarget).request(MediaType.APPLICATION_JSON);
            will(returnValue(mockBuilder));

            oneOf(mockBuilder).post(with(sameBeanAs(Entity.entity(clientValidateRequest, MediaType.APPLICATION_JSON))));
            will(returnValue(mockResponse));

            oneOf(mockResponse).getStatus();
            will(returnValue(200));

            oneOf(mockResponse).readEntity(ClientValidationResponse.class);
            will(returnValue(new ClientValidationResponse()));
        }});


        Response ppsiResponse = ppsiServiceRESTClient.validate(clientValidateRequest);

        assertThat(ppsiResponse.getStatus(), is(200));

        ClientValidationResponse clientValidationResponseValidateResponse = ppsiResponse.readEntity(ClientValidationResponse.class);
        System.out.println(clientValidationResponseValidateResponse);
        assertThat(clientValidationResponseValidateResponse, is(notNullValue()));

    }

}