package nhom8.javabackend.hotel.hotel.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAmenitiesDto {
	
	@NotNull
	private int guestRoom;
	
	@NotNull
	private int bedRoom;
	
	@NotNull
	private boolean wifiAvailability;
	
	@NotNull
	private boolean parkingAvailability;
	
	@NotNull
	private boolean poolAvailability;
	
	@NotNull
	private boolean airCondition;
	
	@NotNull
	private boolean extraBedFacility;
}
