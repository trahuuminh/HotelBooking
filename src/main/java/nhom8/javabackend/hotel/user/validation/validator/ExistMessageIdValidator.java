package nhom8.javabackend.hotel.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.user.service.itf.MessageService;
import nhom8.javabackend.hotel.user.validation.annotation.ExistMessageId;

public class ExistMessageIdValidator implements ConstraintValidator<ExistMessageId, Long> {

	private String message;
	private MessageService service;
	
	public ExistMessageIdValidator(MessageService messageService) {
		service=messageService;
	}
	
	@Override
	public void initialize(ExistMessageId constraintAnnotation) {
		message=constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		boolean isExistId=service.isExistedId(id);
		
		if(isExistId)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}
}
