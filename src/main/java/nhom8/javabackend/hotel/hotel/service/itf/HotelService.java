package nhom8.javabackend.hotel.hotel.service.itf;

import java.util.List;

import javax.validation.Valid;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

public interface HotelService {

	List<HotelDto> findAllDto();

	Hotel addNewHotel(@Valid CreateHotelDto dto);

	Hotel updateHotel(@Valid UpdateHotelDto dto);

	void deleteById(Long hotelId);

	boolean isExistedId(Long hotelId);

}
