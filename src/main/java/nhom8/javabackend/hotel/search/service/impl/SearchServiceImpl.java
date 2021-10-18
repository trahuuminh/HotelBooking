package nhom8.javabackend.hotel.search.service.impl;

import java.util.List;

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
	public List<HotelDto> findHotelByKeyword(String keyword) {
		if (keyword != null) {
			return repository.findHotelByKeyword(keyword);
		}
		return repository.findAllDto();

	}

	@Override
	public List<HotelDto> findHotelByOption(boolean wifiAvailability, boolean parkingAvailability,
			boolean poolAvailability, boolean airCondition, boolean extraBedFacility, String lowPrice, String highPrice,
			int guestRoom, int bedRoom) {
		return repository.findHotelByOption(wifiAvailability, parkingAvailability, poolAvailability, airCondition,
				extraBedFacility, lowPrice, highPrice, guestRoom, bedRoom);
	}

}
