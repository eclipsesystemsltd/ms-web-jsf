package uk.co.meridenspares.web.jsf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LuhnCheckValidatorImpl implements ConstraintValidator<LuhnCheckValidator, String> {

	@Override
	public void initialize(LuhnCheckValidator constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return luhnCheck(value.replaceAll("\\D", ""));
	}
	
	private static boolean luhnCheck(String cardNumber) {
		int sum=0;
		
		for (int i=cardNumber.length() - 1; i >= 0; i -= 2) {
			sum += Integer.parseInt(cardNumber.substring(i, i+1));
			if (i>0) {
				int d = 2 * Integer.parseInt(cardNumber.substring(i-1, i));
				if (d>9) d-= 9;
				sum += d;
			}
		}
		
		return sum % 10 == 0;
	}

}
