package nhom8.javabackend.hotel.review.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nhom8.javabackend.hotel.review.validation.validator.RatingValidator;

@Constraint(validatedBy = RatingValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface Rating {
	public String message() default "Review Values must be between 1 to 5";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
