package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateHotelImagesDto {
	
	@NotNull
	private Long hotelImagesId;
	
	@NotNull
	private String url;
	
	private String thumbUrl;
	
	private Long hotelId;
}
