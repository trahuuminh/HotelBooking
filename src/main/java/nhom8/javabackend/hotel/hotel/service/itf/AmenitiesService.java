package nhom8.javabackend.hotel.hotel.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.hotel.dto.AmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.CreateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;

public interface AmenitiesService {

	List<AmenitiesDto> findAllDto();

	Amenities addNewAmenities(CreateAmenitiesDto dto);

	Amenities updateAmenities(UpdateAmenitiesDto dto);

	void deleteById(Long amenitiesId);

}
