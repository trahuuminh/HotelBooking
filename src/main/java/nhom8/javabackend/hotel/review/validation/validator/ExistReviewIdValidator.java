package nhom8.javabackend.hotel.review.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nhom8.javabackend.hotel.common.utils.ValidatorUtils;
import nhom8.javabackend.hotel.review.service.itf.ReviewService;
import nhom8.javabackend.hotel.review.validation.annotation.ExistReviewId;

public class ExistReviewIdValidator implements ConstraintValidator<ExistReviewId, Long>{

	private String message;
	private ReviewService service;
	
	public ExistReviewIdValidator(ReviewService reviewService) {
		service = reviewService;
	}
	
	@Override
	public void initialize(ExistReviewId constraintAnnotation) {
		message=constraintAnnotation.message();
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
