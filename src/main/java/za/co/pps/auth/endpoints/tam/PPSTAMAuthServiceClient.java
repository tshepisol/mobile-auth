package za.co.pps.auth.endpoints.tam;

import za.co.pps.auth.domain.LoginCredentials;

import javax.inject.Inject;

public class PPSTAMAuthServiceClient {

    @Inject
    private PPSTAMAuthServicePort ppstamAuthServicePort;





    public TAMAuthenticationRs authenticate(LoginCredentials loginCredentials) throws SOAPFormatException_Exception {
        TAMAuthenticationRq tamAuthentication = new TAMAuthenticationRq();
        tamAuthentication.setUsername(loginCredentials.getUsername());
        tamAuthentication.setPassword(loginCredentials.getPassword());

       return ppstamAuthServicePort.tamAuthentication(tamAuthentication);
    }

    public void setPpstamAuthServicePort(PPSTAMAuthServicePort ppstamAuthServicePort) {
        this.ppstamAuthServicePort = ppstamAuthServicePort;
    }
}
