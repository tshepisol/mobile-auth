package za.co.pps.auth.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.endpoints.ppsi.ClientAuthenticateResponse;
import za.co.pps.auth.endpoints.ppsi.ClientValidationResponse;
import za.co.pps.auth.endpoints.tim.GetPersonInfoRs;
import za.co.pps.auth.util.Value;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;


public class PPSJSONWebToken {


    @Inject
    @Value(key = "pps.crypto.secret")
    protected String cryptoSecret;

    @Inject
    @Value(key = "pps.crypto.issuer")
    protected String cryptoIssuer;

    @Inject
    @Value(key = "pps.crypto.expire.inMinutes")
    protected String expire;

    @Inject
    @Value(key = "pps.crypto.aud")
    protected String audience;

    @Inject
    @Value(key = "pps.crypto.schema")
    protected String schema;

    @Inject
    @Value(key = "pps.crypto.role")
    protected String role;


    public String signPPSToken(LoginCredentials loginCredentials, GetPersonInfoRs personInfoRs, ClientValidationResponse clientValidationResponse) throws UnsupportedEncodingException {

        JWTCreator.Builder builder = getBaseBuilder(loginCredentials);

        if (StringUtils.isNotEmpty(clientValidationResponse.getCno()))
            builder.withClaim("client_no", clientValidationResponse.getCno());
        //if(StringUtils.isNotEmpty(personInfoRs.getSurname()))
        builder.withClaim("family_name", personInfoRs.getSurname());
        //if(StringUtils.isNotEmpty(personInfoRs.getName()))
        builder.withClaim("given_name", personInfoRs.getName());
        // if(StringUtils.isNotEmpty(personInfoRs.getIdNumber()))
        builder.withClaim("id_number", personInfoRs.getIdNumber());
        //if(StringUtils.isNotEmpty(clientValidationResponse.getIdtype()))
        builder.withClaim("id_type", clientValidationResponse.getIdtype());
        //if(StringUtils.isNotEmpty(clientValidationResponse.getImeistatus()))
        builder.withClaim("imei_status", clientValidationResponse.getImeistatus());
        if (StringUtils.isNotEmpty(personInfoRs.getMemberNo()))
            builder.withClaim("member_id", personInfoRs.getMemberNo());
        if (StringUtils.isNotEmpty(personInfoRs.getPartyID()))
            builder.withClaim("party_id", personInfoRs.getPartyID());

        return builder
                .withExpiresAt(getExpiryDate()).sign(generateKey());
    }


    public String signPPSIToken(LoginCredentials loginCredentials, ClientAuthenticateResponse clientAuthenticateResponse) throws UnsupportedEncodingException {

        JWTCreator.Builder builder = getBaseBuilder(loginCredentials);

        if (StringUtils.isNotEmpty(clientAuthenticateResponse.getCno()))
            builder.withClaim("client_no", clientAuthenticateResponse.getCno());
        //if(StringUtils.isNotEmpty(clientAuthenticateResponse.getLname()))
        builder.withClaim("family_name", clientAuthenticateResponse.getLname());
        //if(StringUtils.isNotEmpty(clientAuthenticateResponse.getFname()))
        builder.withClaim("given_name", clientAuthenticateResponse.getFname());
        // if(StringUtils.isNotEmpty(clientAuthenticateResponse.getIno()))//TODO VERIFY
        builder.withClaim("id_number", clientAuthenticateResponse.getIno());//TODO VERIFY
        //if(StringUtils.isNotEmpty(clientAuthenticateResponse.getIdtype()))
        builder.withClaim("id_type", clientAuthenticateResponse.getIdtype());
        //if(StringUtils.isNotEmpty(clientAuthenticateResponse.getImeistatus()))
        builder.withClaim("imei_status", clientAuthenticateResponse.getImeistatus());
        if (StringUtils.isNotEmpty(clientAuthenticateResponse.getPpsmember()))
            builder.withClaim("member_id", clientAuthenticateResponse.getPpsmember());
        if (StringUtils.isNotEmpty(clientAuthenticateResponse.getId()))//TODO VERIFY
            builder.withClaim("party_id", clientAuthenticateResponse.getId());//TODO VERIFY


        return builder
                .withExpiresAt(getExpiryDate()).sign(generateKey());
    }

    private JWTCreator.Builder getBaseBuilder(LoginCredentials loginCredentials) {
        return JWT.create()
                .withIssuer(cryptoIssuer)
                .withAudience(audience)
                .withSubject(loginCredentials.getUsername())
                .withClaim(schema, "")
                .withClaim("imei", loginCredentials.getIMEI())
                .withClaim("unique_name", loginCredentials.getUsername())
                .withClaim("role", role)
                .withNotBefore(new Date());

    }


    public DecodedJWT validateToken(String user, String token) throws UnsupportedEncodingException {

        JWTVerifier jwtVerifier = JWT.require(generateKey())
                .withIssuer(cryptoIssuer)
                .withAudience(audience)
                .withSubject(user)
                .build();

        return jwtVerifier.verify(token);
    }

    public DecodedJWT decodeToken(String token) {
        return JWT.decode(token);
    }

    private Algorithm generateKey() throws UnsupportedEncodingException {

        return Algorithm.HMAC256(cryptoSecret);
    }

    private Date getExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Integer.valueOf(expire));
        return calendar.getTime();
    }

    public void setCryptoSecret(String cryptoSecret) {
        this.cryptoSecret = cryptoSecret;
    }

    public void setCryptoIssuer(String cryptoIssuer) {
        this.cryptoIssuer = cryptoIssuer;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
