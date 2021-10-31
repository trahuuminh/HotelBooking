package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import java.util.List;

import lombok.Data;

@Data
public class CreateMultipleHotelImages {
	private List<String> listUrl;
	
	private Long hotelId;
}
