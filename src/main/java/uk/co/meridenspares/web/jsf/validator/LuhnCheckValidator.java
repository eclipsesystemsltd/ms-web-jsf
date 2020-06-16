package uk.co.meridenspares.web.jsf.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy=LuhnCheckValidatorImpl.class)
public @interface LuhnCheckValidator {

	String message() default "{uk.co.meridenspares.web.jsf.validator.LuhnCheckValidator.message}";
	Class[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	
}
