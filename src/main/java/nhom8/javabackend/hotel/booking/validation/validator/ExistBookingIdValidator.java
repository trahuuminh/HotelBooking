package nhom8.javabackend.hotel.booking.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.booking.service.itf.BookingService;
import nhom8.javabackend.hotel.booking.validation.annotation.ExistBookingId;
import nhom8.javabackend.hotel.common.utils.ValidatorUtils;

public class ExistBookingIdValidator implements ConstraintValidator<ExistBookingId, Long> {
	
	private String message;
	private BookingService service;
	
	public ExistBookingIdValidator(BookingService bookingService) {
		service=bookingService;
	}
	
	@Override
	public void initialize(ExistBookingId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		boolean isExist=service.isExistedId(id);
		
		if(isExist)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}
}
