package nhom8.javabackend.hotel.location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLocationDto {

	private float lat;

	private float lng;

	private String formattedAddress;

	private String zipcode;

	private String city;

	private String stateLong;

	private String stateShort;

	private String countryLong;

	private String countryShort;

	private int numberOfPost;
}
