package za.co.pps.auth.domain;

import io.swagger.annotations.ApiModel;

import javax.xml.bind.annotation.XmlRootElement;

@ApiModel
@XmlRootElement
public class SecurityToken {

    private Integer ID;

    private String username;

    private String IMEI;

    private Boolean isValid;

    private String jWTTokenRaw;

    private LogVerbosityEnum logVerbosity;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getjWTTokenRaw() {
        return jWTTokenRaw;
    }

    public void setjWTTokenRaw(String jWTTokenRaw) {
        this.jWTTokenRaw = jWTTokenRaw;
    }

    public LogVerbosityEnum getLogVerbosity() {
        return logVerbosity;
    }

    public void setLogVerbosity(LogVerbosityEnum logVerbosity) {
        this.logVerbosity = logVerbosity;
    }
}
