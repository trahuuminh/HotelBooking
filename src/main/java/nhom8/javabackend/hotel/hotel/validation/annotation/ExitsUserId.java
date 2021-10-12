package nhom8.javabackend.hotel.hotel.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nhom8.javabackend.hotel.hotel.validation.validator.ExitsUserIdValidator;


@Constraint(validatedBy = ExitsUserIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExitsUserId {
	public String message() default "User doesn't exits";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
