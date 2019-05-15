package za.co.pps;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import za.co.pps.auth.service.AuthenticationServiceResource;
import za.co.pps.auth.util.Config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class MobileAuthApplication extends Application {

    HashSet<Object> singletons = new HashSet<>();

    public MobileAuthApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(Config.getSWAGGER_VERSION());
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost(Config.getSWAGGER_HOST());
        beanConfig.setBasePath(Config.getSWAGGER_URL());
        beanConfig.setTitle("PPS Authentication Service");
        beanConfig.setResourcePackage("za.co.pps.auth.service");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<>();

        set.add(AuthenticationServiceResource.class);

        set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}