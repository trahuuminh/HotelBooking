package nhom8.javabackend.hotel.search.service.itf;

import java.util.List;
import java.util.Optional;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;

public interface SearchService {

	List<HotelDto> findHotelByOption(Optional<String> keyword, Optional<String> wifiAvailability, Optional<String> parkingAvailability, Optional<String> poolAvailability,
			Optional<String> airCondition, Optional<String> extraBedFacility, Optional<Integer> lowPrice, Optional<Integer> highPrice, Optional<Integer> guestRoom,
			Optional<Integer> bedRoom);

}
