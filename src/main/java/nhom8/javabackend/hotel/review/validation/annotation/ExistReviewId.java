package nhom8.javabackend.hotel.review.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nhom8.javabackend.hotel.review.validation.validator.ExistReviewIdValidator;

@Constraint(validatedBy = ExistReviewIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistReviewId {
public String message() default "Review doesn't exist";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
