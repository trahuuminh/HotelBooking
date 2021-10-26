package nhom8.javabackend.hotel.search.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.search.service.itf.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private SearchService service;
	private HotelService hotelService;

	public SearchController(SearchService searcService,HotelService HotelService) {
		service = searcService;
		hotelService = HotelService;
	}
	
	@GetMapping("")
	public Object searchHotel(@RequestParam("city") Optional<String> keyword, 
			@RequestParam(value="wifiAvailability") Optional<String> wifiAvailability, 
			@RequestParam(value="parkingAvailability") Optional<String> parkingAvailability, 
			@RequestParam(value="poolAvailability") Optional<String> poolAvailability, 
			@RequestParam(value="airCondition") Optional<String> airCondition, 
			@RequestParam(value="extraBedFacility") Optional<String> extraBedFacility, 
			@RequestParam(value ="lowPrice") Optional<Integer> lowPrice, 
			@RequestParam(value="highPrice") Optional<Integer> highPrice, 
			@RequestParam(value="guestRoom") Optional<Integer> guestRoom, 
			@RequestParam(value="bedRoom") Optional<Integer> bedRoom,
			@RequestParam("page") Optional<Integer> page) {
		try {
			Pageable pageable=PageRequest .of(page.orElse(0),22,Sort.by("id"));
			
			Page<HotelDto> listHotels = service.findHotelByOption(keyword, wifiAvailability, parkingAvailability, poolAvailability, 
					airCondition, extraBedFacility, lowPrice, highPrice, guestRoom, bedRoom,pageable);

			return ResponseHandler.getResponse(hotelService.pagingFormat(listHotels), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("No hotels found",HttpStatus.BAD_REQUEST);
		}
	}
}
