package za.co.pps.auth.endpoints.ppsi;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.util.Value;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class PPSIServiceRESTClient {


    @Inject
    @Value(key = "ppsi.service.rest.url")
    private String ppsiURL;

    private Client client;


    @PostConstruct
    public void init(){
        ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        config.register(JacksonJsonProvider.class);
        client = ClientBuilder.newClient(config);
    }


    public Response authenticate(LoginCredentials loginCredentials){
        return  client.target(ppsiURL).path("/authenticate")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(new AuthenticationRequest(loginCredentials.getUsername(), loginCredentials.getPassword()), MediaType.APPLICATION_JSON));
    }

    public Response validate(ClientValidateRequest clientValidateRequest){
        return  client.target(ppsiURL).path("/client/validate")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(clientValidateRequest, MediaType.APPLICATION_JSON));

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPpsiURL(String ppsiURL) {
        this.ppsiURL = ppsiURL;
    }
}
