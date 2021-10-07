package nhom8.javabackend.hotel.hotel.service.itf;

import java.util.List;

import javax.validation.Valid;

import nhom8.javabackend.hotel.hotel.dto.AmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.CreateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;

public interface AmenitiesService {

	List<AmenitiesDto> findAllDto();

	Amenities addNewAmenities(@Valid CreateAmenitiesDto dto);

	Amenities updateAmenities(@Valid UpdateAmenitiesDto dto);

	void deleteById(Long amenitiesId);

}
