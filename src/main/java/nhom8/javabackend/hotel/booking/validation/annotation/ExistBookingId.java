package nhom8.javabackend.hotel.booking.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nhom8.javabackend.hotel.booking.validation.validator.ExistBookingIdValidator;

@Constraint(validatedBy = ExistBookingIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistBookingId {
	public String message() default "Booking doesn't exist";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
