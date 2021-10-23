package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateHotelImagesDto {
	@NotNull
	private String url;
	
	private String thumbUrl;
	
	private Long hotelId;
	
	public CreateHotelImagesDto(String url,String thumbUrl,Long hotelId) {
		this.url=url;
		this.thumbUrl=thumbUrl;
		this.hotelId=hotelId;
	}
}
