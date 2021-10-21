package nhom8.javabackend.hotel.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.validation.annotation.ExistUsername;

public class ExistUsernameValidator implements ConstraintValidator<ExistUsername, String> {

	private String message;
	private UserService userService;
	
	public ExistUsernameValidator(UserService service) {
		userService=service;
	}
	
	@Override
	public void initialize(ExistUsername constraintAnnotation) {
		message=constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userService.getUserByUsername(username)==null)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
