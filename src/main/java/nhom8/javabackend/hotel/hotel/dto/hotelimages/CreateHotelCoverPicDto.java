package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHotelCoverPicDto {

	private String url;
	
	private String thumbUrl;
	
	public CreateHotelCoverPicDto(String url,String thumbUrl) {
		this.url=url;
		this.thumbUrl=thumbUrl;
	}
}
