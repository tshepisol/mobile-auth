package za.co.pps.auth.service.integration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Before;
import org.junit.Test;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.domain.SecurityToken;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AuthenticationServiceIT {

    private Client client;
    private ResourceBundle bundle = ResourceBundle.getBundle("auth-int");


    @Before
    public void setup(){
        ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        config.register(JacksonJsonProvider.class);
        client = ClientBuilder.newClient(config);
    }

    @Test
    public void testPPS_authenticate(){
        Response response = authenticatePPS("nkana", "Password1");

        assertThat(response.getStatus(), is(200));
        assertThat(response.hasEntity(), is(true));

        SecurityToken securityToken = response.readEntity(SecurityToken.class);
        System.out.println(securityToken.getjWTTokenRaw());

        assertThat(securityToken.getValid(), is(true));
        assertThat(securityToken.getjWTTokenRaw(), is(notNullValue()));

    }



    @Test
    public void testPPS_verify(){
        Response responseAuthenticate = authenticatePPS("nkana", "Password1");

        SecurityToken securityToken = responseAuthenticate.readEntity(SecurityToken.class);

        Response responseValidate = client.target(bundle.getString("pps.auth.url")).path("1.0/authenticate/verify")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(securityToken, MediaType.APPLICATION_JSON));

        assertThat(responseValidate.getStatus(), is(200));

    }


    @Test
    public void testPPSI_authenticate(){
        Response response = authenticatePPS("swartt2", "D3vt3sting#");

        assertThat(response.getStatus(), is(200));
        assertThat(response.hasEntity(), is(true));

        SecurityToken securityToken = response.readEntity(SecurityToken.class);
        System.out.println(securityToken.getjWTTokenRaw());

        assertThat(securityToken.getValid(), is(true));
        assertThat(securityToken.getjWTTokenRaw(), is(notNullValue()));

    }

    @Test
    public void testPPSI_verify(){
        Response responseAuthenticate = authenticatePPS("swartt2", "D3vt3sting#");

        SecurityToken securityToken = responseAuthenticate.readEntity(SecurityToken.class);

        Response responseValidate = client.target(bundle.getString("pps.auth.url")).path("1.0/authenticate/verify")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(securityToken, MediaType.APPLICATION_JSON));

        assertThat(responseValidate.getStatus(), is(200));

    }


    private Response authenticatePPS(String username, String password) {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(username);
        loginCredentials.setPassword(password);
        loginCredentials.setIMEI("IMEI");

        return client.target(bundle.getString("pps.auth.url")).path("/1.0/Authenticate")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginCredentials, MediaType.APPLICATION_JSON));
    }
}
