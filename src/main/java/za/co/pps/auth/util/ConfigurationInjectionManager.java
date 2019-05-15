package za.co.pps.auth.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationInjectionManager {

	static final String INVALID_KEY="Invalid key '{0}'";
    static final String MANDATORY_PARAM_MISSING = "No definition found for a mandatory configuration parameter : '{0}'";
    private final String BUNDLE_FILE_NAME = "auth";
    private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_FILE_NAME);
    
    @Produces
    @Value
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {
        Value param = ip.getAnnotated().getAnnotation(Value.class);
        if (param.key() == null || param.key().length() == 0) {
            return param.defaultValue();
        }
        String value;
        try {
            value = bundle.getString(param.key());
            if (value == null || value.trim().length() == 0) {
                if (param.mandatory())
                    throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING, new Object[]{param.key()}));
                else
                    return param.defaultValue();
            }
            return value;            
        } catch (MissingResourceException e) {
            if (param.mandatory()) throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING, new Object[]{param.key()}));
            return MessageFormat.format(INVALID_KEY, new Object[]{param.key()});
        }
    }
}
