package nhom8.javabackend.hotel.search.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.search.repository.SearchRepository;
import nhom8.javabackend.hotel.search.service.itf.SearchService;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	private SearchRepository repository;

	public SearchServiceImpl(SearchRepository searchRepository) {

		repository = searchRepository;

	}

	@Override
	public List<HotelDto> findHotelByOption(Optional<String> keyword, Optional<String> wifiAvailability, Optional<String> parkingAvailability,
			Optional<String> poolAvailability, Optional<String> airCondition, Optional<String> extraBedFacility, Optional<Integer> lowPrice, Optional<Integer> highPrice,
			Optional<Integer> guestRoom, Optional<Integer> bedRoom) {
		
		return repository.findHotelByOption(keyword, wifiAvailability, parkingAvailability, poolAvailability,airCondition, extraBedFacility, 
				lowPrice , highPrice, guestRoom, bedRoom);
	}

}
