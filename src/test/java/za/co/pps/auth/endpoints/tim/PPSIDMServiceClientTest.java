package za.co.pps.auth.endpoints.tim;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class PPSIDMServiceClientTest {

    PPSIDMServiceClient ppsidmServiceClient;
    PPSIDMServicePort mockPpsidmServicePort;


    Mockery mockery;

    @Before
    public void setup() {

        ppsidmServiceClient = new PPSIDMServiceClient();


        mockery = new Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};

        mockPpsidmServicePort = mockery.mock(PPSIDMServicePort.class);
        ppsidmServiceClient.setPpsidmServicePort(mockPpsidmServicePort);
    }

    @Test
    public void testPersonalInfo() throws SOAPFormatException_Exception {

        mockery.checking(new Expectations(){{
            oneOf(mockPpsidmServicePort).getPersonInfo(with(sameBeanAs(new GetPersonInfoRq("nkana"))));
            will(returnValue(getPersonInfoRs()));
        }});

        GetPersonInfoRs personInfoRs = ppsidmServiceClient.getPersonalInfo("nkana");
        System.out.println(personInfoRs);
        assertThat(personInfoRs, is(notNullValue()));
        assertThat(personInfoRs, sameBeanAs(getPersonInfoRs()));
    }

    private GetPersonInfoRs getPersonInfoRs(){
        GetPersonInfoRs getPersonInfoRs = new GetPersonInfoRs();
        getPersonInfoRs.setResultCode("R00");
        getPersonInfoRs.setResultMessage("User Get Info Succesful");
        getPersonInfoRs.setUserType("PPS");

        return getPersonInfoRs;
    }

}