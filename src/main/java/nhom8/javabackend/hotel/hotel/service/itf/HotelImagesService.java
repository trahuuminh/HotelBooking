package nhom8.javabackend.hotel.hotel.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.UpdateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;

public interface HotelImagesService {
	List<HotelImagesDto> findAllHotelImagesDto();
	
	HotelImages createNewHotelImages(CreateHotelImagesDto dto);
	
	HotelImages updateHotelImages(UpdateHotelImagesDto dto);
	
	void deleteHotelImages(Long id);
}