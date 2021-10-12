package nhom8.javabackend.hotel.user.dto;

import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

@Getter
@Setter
public class AddHotelDto {
	
	@NotNull
	@Min(value = 1)
	@ExitsUserId
	private Long userId;

	@NotNull
	@Min(value = 1)
	@ExitsHotelId
	private Long hotelId;
}
