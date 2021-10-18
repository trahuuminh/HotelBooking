package nhom8.javabackend.hotel.search.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchHotelDto {

	@NotNull
	private String wifiAvailability;

	@NotNull
	private String parkingAvailability;

	@NotNull
	private String poolAvailability;

	@NotNull
	private String airCondition;

	@NotNull
	private String extraBedFacility;

	@NotNull
	private String lowPrice;

	@NotNull
	private String highPrice;

	@NotNull
	private int guestRoom;

	@NotNull
	private int bedRoom;
}
