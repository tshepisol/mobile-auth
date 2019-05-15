package za.co.pps.auth.util;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface Value {
	/**
     * Bundle key
     * @return a valid bundle key or ""
     */
    @Nonbinding String key() default "";
    /**
     * Is it a mandatory property
     * @return true if mandator
     */
    @Nonbinding boolean mandatory() default false;
    /**
     * Default value if not provided
     * @return default value or ""
     */
    @Nonbinding String defaultValue() default "";
}
