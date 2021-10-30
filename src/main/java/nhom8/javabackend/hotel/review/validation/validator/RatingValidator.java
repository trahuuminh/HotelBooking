package nhom8.javabackend.hotel.review.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.review.validation.annotation.Rating;

public class RatingValidator implements ConstraintValidator<Rating, Integer> {

	String message;
	
	@Override
	public void initialize(Rating constraintAnnotation) {
		message=constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value >=1 && value <=5)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
