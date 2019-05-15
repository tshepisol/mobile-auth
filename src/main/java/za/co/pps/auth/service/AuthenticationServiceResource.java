package za.co.pps.auth.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.pps.auth.domain.LoginCredentials;
import za.co.pps.auth.domain.SecurityToken;
import za.co.pps.auth.endpoints.ppsi.ClientAuthenticateResponse;
import za.co.pps.auth.endpoints.ppsi.ClientValidateRequest;
import za.co.pps.auth.endpoints.ppsi.ClientValidationResponse;
import za.co.pps.auth.endpoints.ppsi.PPSIServiceRESTClient;
import za.co.pps.auth.endpoints.tam.*;
import za.co.pps.auth.endpoints.tim.GetPersonInfoRs;
import za.co.pps.auth.endpoints.tim.PPSIDMServiceClient;
import za.co.pps.auth.security.PPSJSONWebToken;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import static za.co.pps.auth.util.Contants.INVALID;
import static za.co.pps.auth.util.Contants.NOT_FOUND;
import static za.co.pps.auth.util.Contants.SUCCESS;


@Path("/")
@Api(value = "/api", description = "PPS Authentication API")
@Produces({MediaType.APPLICATION_JSON, "application/x-javascript", "text/javascript", "text/x-json"})
@Consumes({MediaType.APPLICATION_JSON, "application/x-javascript", "text/javascript", "text/x-json"})
public class AuthenticationServiceResource {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceResource.class);

    @Inject
    protected PPSTAMAuthServiceClient ppstamAuthServiceClient;

    @Inject
    protected PPSIDMServiceClient ppsidmServiceClient;

    @Inject
    protected PPSIServiceRESTClient ppsiServiceRESTClient;

    @Inject
    protected PPSJSONWebToken ppsjsonWebToken;


    private Validator validator;


    @PostConstruct
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //TODO DONT COMMIT
    @GET
    @Path("/test")
    public Response test(){

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername("nkana");
        loginCredentials.setPassword("Password1");
        loginCredentials.setIMEI("IMEI");
        return   authenticate("",loginCredentials);
    }

    @ApiOperation(value = "Used to authenticate user credentials against PPS",
            response = SecurityToken.class,
            notes = "Required Fields are Username, Password, IMEI and PublicEncryptionKey. A RSA public/private key pair must be generated and the public key passed in as part of the authentication process. The corresponding private key must then be used to decrypt the encrypted fields. The JWT token returned in this call must be added as a Authorization request header to all other service calls to pass authentication (i.e. Authorization : Bearer {JWTTokenRaw} )")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 500, message = "Internal Server Error")})

    @POST
    @Path("/{version}/Authenticate")
    public Response authenticate(
            @ApiParam(value = "API version", required = true, defaultValue = "1.0")
            @PathParam("version") String version, LoginCredentials loginCredentials) {

        SecurityToken securityToken = getSecurityToken(loginCredentials);
        String signToken = null;

        try {
            //BEAN VALIDATION
            validate(loginCredentials);

            //TAM
            TAMAuthenticationRs tamAuthenticationResponse = ppstamAuthServiceClient.authenticate(loginCredentials);

            switch (tamAuthenticationResponse.getResultCode()) {
                case SUCCESS:
                    //TIM getPersonalInfo
                    GetPersonInfoRs personInfoRs = ppsidmServiceClient.getPersonalInfo(loginCredentials.getUsername());

                    //PPSI validate
                    Response ppsiValidateResponse = ppsiServiceRESTClient.validate(new ClientValidateRequest(personInfoRs.getMemberNo(), personInfoRs.getIdNumber()));
                    ClientValidationResponse clientValidationResponse = ppsiValidateResponse.readEntity(ClientValidationResponse.class);

                    //sign TOKEN
                    signToken = ppsjsonWebToken.signPPSToken(loginCredentials, personInfoRs, clientValidationResponse);
                    break;
                case NOT_FOUND:
                    //PPSI authenticate
                    Response ppsiAuthResponse = ppsiServiceRESTClient.authenticate(loginCredentials);

                    if (Response.Status.OK.getStatusCode() == ppsiAuthResponse.getStatus()) {
                        ClientAuthenticateResponse clientAuthenticateResponse = ppsiAuthResponse.readEntity(ClientAuthenticateResponse.class);
                        //call IAA //TODO
                        //sign TOKEN
                        signToken = ppsjsonWebToken.signPPSIToken(loginCredentials, clientAuthenticateResponse);
                    } else
                        return sendFailedResponse(Response.Status.BAD_REQUEST, securityToken);

                    break;
                case INVALID:
                    return sendFailedResponse(Response.Status.BAD_REQUEST, securityToken);
            }


            securityToken.setValid(true);
            securityToken.setjWTTokenRaw(signToken);

        }catch (ValidationException ve){
            log.error("Bean validation exception:", ve);
            return sendFailedResponse(Response.Status.BAD_REQUEST, securityToken);
        } catch (Exception e) {
            log.error("failed to authenticate, service error", e);
            return sendFailedResponse(Response.Status.INTERNAL_SERVER_ERROR, securityToken);
        }

        return Response.ok(securityToken).build();
    }


    @ApiOperation(value = "Verifies the specified token")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 500, message = "Internal Server Error")})

    @POST
    @Path("{version}/authenticate/verify")
    public Response validate(
            @ApiParam(value = "API version", required = true, defaultValue = "1.0")
            @PathParam("version")  String version, SecurityToken securityToken) {

        try {
            ppsjsonWebToken.validateToken(securityToken.getUsername(), securityToken.getjWTTokenRaw());
        } catch (JWTVerificationException | UnsupportedEncodingException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    private Response sendFailedResponse(Response.Status status, SecurityToken securityToken){
        securityToken.setValid(false);
        return Response.status(status).entity(securityToken).build();
    }


    private SecurityToken getSecurityToken(LoginCredentials loginCredentials) {
        SecurityToken securityToken = new SecurityToken();
        securityToken.setUsername(loginCredentials.getUsername());
        securityToken.setIMEI(loginCredentials.getIMEI());
        return securityToken;
    }

    private void validate(LoginCredentials loginCredentials){
        Set<ConstraintViolation<LoginCredentials>> validate = validator.validate(loginCredentials);
        if(!validate.isEmpty()) {
            ConstraintViolation constraintViolation =   validate.iterator().next();
            throw new ValidationException(constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage());
        }
    }

    public void setPpsjsonWebToken(PPSJSONWebToken ppsjsonWebToken) {
        this.ppsjsonWebToken = ppsjsonWebToken;
    }
}
