package gr.dkateros.util.jee7.validation;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;

public class BeanValidationUtility {
	
	@Produces
	public Validator getValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

}
