package za.co.pps.auth.domain;


import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel
@XmlRootElement
public class LoginCredentials {

    private String authenticationSource;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String IMEI;

    private String clientID;

    private String publicEncryptionKey;


    public String getAuthenticationSource() {
        return authenticationSource;
    }

    public void setAuthenticationSource(String authenticationSource) {
        this.authenticationSource = authenticationSource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getPublicEncryptionKey() {
        return publicEncryptionKey;
    }

    public void setPublicEncryptionKey(String publicEncryptionKey) {
        this.publicEncryptionKey = publicEncryptionKey;
    }
}

