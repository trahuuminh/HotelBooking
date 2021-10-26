package nhom8.javabackend.hotel.search.service.itf;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;

public interface SearchService {

	Page<HotelDto> findHotelByOption(Optional<String> keyword, Optional<String> wifiAvailability, Optional<String> parkingAvailability, Optional<String> poolAvailability,
			Optional<String> airCondition, Optional<String> extraBedFacility, Optional<Integer> lowPrice, Optional<Integer> highPrice, Optional<Integer> guestRoom,
			Optional<Integer> bedRoom,Pageable pageable );

}
