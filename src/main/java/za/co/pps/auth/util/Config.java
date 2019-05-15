package za.co.pps.auth.util;

import java.util.ResourceBundle;

public class Config {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("auth");

    private static String PPS_TAM, PPS_IDM, SWG_VERSION, SWG_HOST, SWG_URL;


    public static String getTAMWSDL(){
        if(PPS_TAM == null)
            PPS_TAM = bundle.getString("pps.tam.ws.url");
        return PPS_TAM;
    }

    public static String getIDMWSDL(){
        if(PPS_IDM == null)
            PPS_IDM = bundle.getString("pps.idm.ws.url");
        return PPS_IDM;
    }

    public static String getSWAGGER_VERSION(){
        if(SWG_VERSION == null)
            SWG_VERSION = bundle.getString("pps.auth.swagger.apiVersion");
        return SWG_VERSION;
    }

    public static String getSWAGGER_URL(){
        if(SWG_URL == null)
            SWG_URL = bundle.getString("pps.auth.swagger.baseUrl");
        return SWG_URL;
    }

    public static String getSWAGGER_HOST(){
        if(SWG_HOST == null)
            SWG_HOST = bundle.getString("pps.auth.swagger.host");
        return SWG_HOST;
    }
}
