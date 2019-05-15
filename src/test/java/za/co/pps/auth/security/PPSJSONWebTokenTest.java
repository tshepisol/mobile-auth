package za.co.pps.auth.security;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Before;
import org.junit.Test;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.endpoints.ppsi.ClientValidationResponse;
import za.co.pps.auth.endpoints.tim.GetPersonInfoRs;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PPSJSONWebTokenTest {

    PPSJSONWebToken ppsjsonWebToken;

    @Before
    public void setup(){
        ppsjsonWebToken = new PPSJSONWebToken();
        ppsjsonWebToken.expire = "1"; //1 Minute
        ppsjsonWebToken.cryptoIssuer = "user";
        ppsjsonWebToken.cryptoSecret = "pass";
        ppsjsonWebToken.audience = "GUID";
        ppsjsonWebToken.schema = "http://pps";
        ppsjsonWebToken.role = "PPS Role";

    }

    @Test
    public void testSign() throws UnsupportedEncodingException {
        String token = ppsjsonWebToken.signPPSToken(getLoginCredentials("tletsw", "pass", "IMEI"), getPersonInfoRs(), getClientValidationResponse());
        System.out.println(token);
        assertThat(token, is(notNullValue()));

        String[] parsedToken = token.split("\\.");
        assertThat(parsedToken.length, is(3));
    }

    @Test
    public void testTokenValidation() throws UnsupportedEncodingException {

        String token = ppsjsonWebToken.signPPSToken(getLoginCredentials("tletsw", "pass", "IMEI"), getPersonInfoRs(), getClientValidationResponse());

        DecodedJWT decodedJWT = ppsjsonWebToken.validateToken("tletsw", token);

        assertThat(decodedJWT, is(notNullValue()));
    }


    @Test(expected = InvalidClaimException.class)
    public void testTokenWrongAudience() throws UnsupportedEncodingException {
        String token = ppsjsonWebToken.signPPSToken(getLoginCredentials("tletsw", "pass", "IMEI"), getPersonInfoRs(), getClientValidationResponse());

        ppsjsonWebToken.validateToken("user", token);
    }

    @Test(expected = JWTVerificationException.class)
    public void testTokenExpiry() throws UnsupportedEncodingException, InterruptedException {

        String token = ppsjsonWebToken.signPPSToken(getLoginCredentials("tletsw", "pass", "IMEI"), getPersonInfoRs(), getClientValidationResponse());
        Thread.sleep(62000);
        ppsjsonWebToken.validateToken("tletsw", token);
    }

    private GetPersonInfoRs getPersonInfoRs() {
        GetPersonInfoRs personInfoRs = new GetPersonInfoRs();
        personInfoRs.setIdNumber("800808");
        personInfoRs.setMemberNo("4321");
        personInfoRs.setName("Tshepiso");
        personInfoRs.setSurname("Letswalo");

        return personInfoRs;
    }

    private ClientValidationResponse getClientValidationResponse() {
        ClientValidationResponse clientValidationResponse= new ClientValidationResponse();
        clientValidationResponse.setIdtype("ID");
        clientValidationResponse.setImeistatus("OK");
        return clientValidationResponse;
    }


    private LoginCredentials getLoginCredentials(String username, String password, String IMEI) {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(username);
        loginCredentials.setPassword(password);
        loginCredentials.setIMEI(IMEI);
        return loginCredentials;
    }
}