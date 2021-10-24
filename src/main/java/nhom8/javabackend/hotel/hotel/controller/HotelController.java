package nhom8.javabackend.hotel.hotel.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.service.itf.HotelImagesService;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.service.itf.UserService;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
	private HotelService service;
	private JwtUtils jwt;
	private UserService userService;
	private final String uploadDir="/src/main/resources/static/hotel-images/";
	private HotelImagesService hotelImagesService;
	
	public HotelController(HotelService hotelService,UserService UserService,JwtUtils Jwt, HotelImagesService HotelImagesService) {
		service = hotelService;
		jwt=Jwt;
		userService = UserService;
		hotelImagesService=HotelImagesService;
	}

	@GetMapping
	public Object findAllHotels(@RequestParam("p") Optional<Integer> p) {
		Pageable pageable= PageRequest.of(p.orElse(0), 5,Sort.by("id"));
		Page<HotelDto> hotels = service.findAllHotel(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(hotels), HttpStatus.OK);

	}

	@PostMapping("/add-hotel")
	public Object addNewHotel(@RequestBody CreateHotelDto dto, BindingResult errors, HttpServletRequest request) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		else if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("Please sign in first if you want to post your hotel",HttpStatus.BAD_REQUEST);
		
		Hotel addedHotel = service.addNewHotel(dto,userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));

		return ResponseHandler.getResponse(addedHotel, HttpStatus.CREATED);
	}

	@PutMapping("/update-hotel")
	public Object updateHotel(@Valid @RequestBody UpdateHotelDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Hotel updateHotel = service.updateHotel(dto);

		return ResponseHandler.getResponse(updateHotel, HttpStatus.OK);
	}

	@DeleteMapping("/delete-hotel/{hotel-id}")
	public Object deleteHotel(@PathVariable("hotel-id") Long hotelId) {
		if(!service.isExistedId(hotelId))
			return ResponseHandler.getResponse("Hotel doesn't exist",HttpStatus.BAD_REQUEST);
		else if(service.getHotelByHotelId(hotelId).getBookings().size()>0)
			return ResponseHandler.getResponse("Please delete all bookings before delete hotel !",HttpStatus.BAD_REQUEST);
		
		service.deleteById(hotelId);

		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@PostMapping("/upload-hotel-cover-pic/{hotel-id}")
	public Object uploadHotelCoverPic(@RequestParam("file") MultipartFile file,@PathVariable("hotel-id")Long hotelId) {
		try {
			Calendar date= Calendar.getInstance();
			String fileName = date.getTimeInMillis()+"-"+file.getOriginalFilename();
			
			String userDirectory=Paths.get("").toAbsolutePath().toString();
			
			Path folderPath = Paths.get(userDirectory + uploadDir);
			
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			
			Path path = Paths.get(userDirectory + uploadDir + fileName);
			
			Files.write(path, file.getBytes());
			CreateHotelImagesDto dto=new CreateHotelImagesDto(fileName,userDirectory + uploadDir + fileName,hotelId);
			HotelImages hotelImage=hotelImagesService.createNewHotelImages(dto);
			
			Hotel hotel=service.getHotelByHotelId(hotelId);
			
			service.setHotelCoverPic(hotel, hotelImage);
			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
	
}
