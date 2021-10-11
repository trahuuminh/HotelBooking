package nhom8.javabackend.hotel.hotel.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;
import nhom8.javabackend.hotel.user.service.itf.UserService;

public class ExitsUserIdValidator implements ConstraintValidator<ExitsUserId, Long>{

	private String message;
	private UserService service;
	
	public ExitsUserIdValidator(UserService userService) {
		service = userService;
	}
	
	
	@Override
	public void initialize(ExitsUserId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Long userId, ConstraintValidatorContext context) {
		boolean isExisted = service.isExistedId(userId);
		
		if(isExisted)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
