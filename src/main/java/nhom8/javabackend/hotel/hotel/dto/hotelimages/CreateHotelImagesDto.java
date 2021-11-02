package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateHotelImagesDto {
	@NotNull
	private String url;
	
	private Long hotelId;

}
