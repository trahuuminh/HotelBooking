package nhom8.javabackend.hotel.common.utils;

import javax.validation.ConstraintValidatorContext;

public class ValidatorUtils {
	public static void addError(ConstraintValidatorContext context, String message) {
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
	}
}
