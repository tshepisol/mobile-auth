package za.co.pps.auth.util;

import za.co.pps.auth.endpoints.tam.PPSTAMAuthService;
import za.co.pps.auth.endpoints.tam.PPSTAMAuthServicePort;
import za.co.pps.auth.endpoints.tim.PPSIDMService;
import za.co.pps.auth.endpoints.tim.PPSIDMServicePort;

import javax.enterprise.inject.Produces;

public class ResourceProducer {

    @Produces
    public PPSTAMAuthServicePort getPPSTAMAuthService(){
        return new PPSTAMAuthService().getPPSTAMAuthServicePort();

    }

    @Produces
    public PPSIDMServicePort getPPSIDMService(){
        return new PPSIDMService().getPPSIDMServicePort();
    }

}
