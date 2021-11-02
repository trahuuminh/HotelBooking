package nhom8.javabackend.hotel.user.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nhom8.javabackend.hotel.user.validation.validator.ExitsHotelIdValidator;

@Constraint(validatedBy = ExitsHotelIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExitsHotelId {
	public String message() default "Hotel doesn't exist";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
