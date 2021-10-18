package nhom8.javabackend.hotel.search.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;

public interface SearchService {

	List<HotelDto> findHotelByKeyword(String keyword);

	List<HotelDto> findHotelByOption(boolean wifiAvailability, boolean parkingAvailability, boolean poolAvailability,
			boolean airCondition, boolean extraBedFacility, String lowPrice, String highPrice, int guestRoom,
			int bedRoom);

}
