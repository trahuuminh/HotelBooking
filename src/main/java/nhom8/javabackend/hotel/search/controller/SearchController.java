package nhom8.javabackend.hotel.search.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.search.service.itf.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private SearchService service;

	public SearchController(SearchService searcService) {
		service = searcService;
	}

	@GetMapping("/keyword")
	public Object findHotelByKeyword(@RequestParam("city") String keyword) {
		List<HotelDto> listHotels = service.findHotelByKeyword(keyword);

		return ResponseHandler.getResponse(listHotels, HttpStatus.OK);
	}
	
	@GetMapping("/option")
	public Object searchHotel(@RequestParam("wifiAvailability") boolean wifiAvailability, 
			@RequestParam("parkingAvailability") boolean parkingAvailability, 
			@RequestParam("poolAvailability") boolean poolAvailability, 
			@RequestParam("airCondition") boolean airCondition, 
			@RequestParam("extraBedFacility") boolean extraBedFacility, 
			@RequestParam("lowPrice") String lowPrice, 
			@RequestParam("highPrice") String highPrice, 
			@RequestParam("guestRoom") int guestRoom, 
			@RequestParam("bedRoom") int bedRoom) {
		List<HotelDto> listHotels = service.findHotelByOption(wifiAvailability, parkingAvailability, poolAvailability, airCondition, extraBedFacility, lowPrice, highPrice, guestRoom, bedRoom);

		return ResponseHandler.getResponse(listHotels, HttpStatus.OK);
	}
}
