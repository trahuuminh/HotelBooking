package nhom8.javabackend.hotel.hotel.service.itf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.PagingFormatHotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.user.entity.User;

public interface HotelService {

	Page<HotelDto> findAllHotel(Pageable pageable);

	PagingFormatHotelDto pagingFormat (Page<HotelDto> page);
	
	Hotel addNewHotel(CreateHotelDto dto,User agent);

	Hotel updateHotel(UpdateHotelDto dto);

	void deleteById(Long hotelId);
	
	boolean isExistedId(Long hotelId);

	Hotel getHotelByHotelId(Long hotelId);
	
	Hotel setHotelCoverPic(Hotel hotel,HotelImages image);

	HotelDto getHotelBySlugName(String slug);

	Page<HotelDto> FindHotelByMostBooking(Pageable pageable);

}
