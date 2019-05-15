package za.co.pps.auth.service.integration.tim;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import za.co.pps.auth.endpoints.tim.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


public class PPSIDMServiceIT {

    PPSIDMServicePort ppsidmServicePort;

    @Before
    public void setUp() throws Exception {
        ppsidmServicePort = new PPSIDMService().getPPSIDMServicePort();
    }

    @Test
    public void testGetPersonalInfo() throws SOAPFormatException_Exception {

        GetPersonInfoRq getPersonInfoRq = new GetPersonInfoRq();
        getPersonInfoRq.setUsername("nkana");
        getPersonInfoRq.setUserType("PPS");

        GetPersonInfoRs personInfo = ppsidmServicePort.getPersonInfo(getPersonInfoRq);

        System.out.println("personInfo:"+personInfo);

        assertThat(personInfo, is(notNullValue()));
        assertThat(personInfo.getResultCode(), is("R00"));
    }

}