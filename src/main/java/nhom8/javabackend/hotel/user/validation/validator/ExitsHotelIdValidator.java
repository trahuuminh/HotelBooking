package nhom8.javabackend.hotel.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

public class ExitsHotelIdValidator implements ConstraintValidator<ExitsHotelId, Long>{

	private String message;
	private HotelService service;
	
	public ExitsHotelIdValidator(HotelService hotelService) {
		service = hotelService;
	}
	
	
	
	@Override
	public void initialize(ExitsHotelId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Long hotelId, ConstraintValidatorContext context) {
		boolean isExisted = service.isExistedId(hotelId);
		
		if(isExisted)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}
}
