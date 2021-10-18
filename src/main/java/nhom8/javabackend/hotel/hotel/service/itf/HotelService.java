package nhom8.javabackend.hotel.hotel.service.itf;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.PagingFormatHotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

public interface HotelService {

	Page<HotelDto> findAllHotel(Pageable pageable);

	PagingFormatHotelDto pagingFormat (Page<HotelDto> page);
	
	Hotel addNewHotel(@Valid CreateHotelDto dto);

	Hotel updateHotel(@Valid UpdateHotelDto dto);

	void deleteById(Long hotelId);
	
	boolean isExistedId(Long hotelId);

}
