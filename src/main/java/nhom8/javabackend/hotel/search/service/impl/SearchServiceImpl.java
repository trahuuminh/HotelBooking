package nhom8.javabackend.hotel.search.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<HotelDto> findHotelByOption(Optional<String> keyword, 
											Optional<Boolean> wifiAvailability, 
											Optional<Boolean> parkingAvailability,
											Optional<Boolean> poolAvailability, 
											Optional<Boolean> airCondition, 
											Optional<Boolean> extraBedFacility, 
											Optional<Integer> lowPrice, 
											Optional<Integer> highPrice,
											Optional<Integer> guestRoom, 
											Optional<Integer> bedRoom,
											Pageable pageable) {
		
		return repository.findHotelByOption(keyword, 
											wifiAvailability, 
											parkingAvailability, 
											poolAvailability,
											airCondition, 
											extraBedFacility, 
											lowPrice, 
											highPrice, 
											guestRoom, 
											bedRoom,
											pageable);
	}

}
