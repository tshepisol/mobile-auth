package za.co.pps.auth.endpoints.tim;

import javax.inject.Inject;

public class PPSIDMServiceClient {


    @Inject
    private PPSIDMServicePort ppsidmServicePort;

    public GetPersonInfoRs getPersonalInfo(String username) throws SOAPFormatException_Exception {

       return ppsidmServicePort.getPersonInfo(new GetPersonInfoRq(username));
    }

    public void setPpsidmServicePort(PPSIDMServicePort ppsidmServicePort) {
        this.ppsidmServicePort = ppsidmServicePort;
    }
}
