package nhom8.javabackend.hotel.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.validation.annotation.ExistEmail;

public class ExistEmailValidator implements ConstraintValidator<ExistEmail, String> {

	private String message;
	private UserService userService;
	
	public ExistEmailValidator(UserService service) {
		userService=service;
	}
	
	@Override
	public void initialize(ExistEmail constraintAnnotation) {
		message=constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(userService.getUserByEmail(email)==null)
			return true;

		ValidatorUtils.addError(context, message);
		return false;
	}
	
	
}
