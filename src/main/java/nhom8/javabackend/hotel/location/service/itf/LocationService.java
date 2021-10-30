package nhom8.javabackend.hotel.location.service.itf;

import java.util.List;

import javax.validation.Valid;

import nhom8.javabackend.hotel.location.dto.CreateLocationDto;
import nhom8.javabackend.hotel.location.dto.ListOfCityDto;
import nhom8.javabackend.hotel.location.dto.LocationDto;
import nhom8.javabackend.hotel.location.dto.UpdateLocationDto;
import nhom8.javabackend.hotel.location.entity.Location;

public interface LocationService {

	List<LocationDto> findAllDto();

	Location addNewLocation(@Valid CreateLocationDto dto);

	Location updateLocation(@Valid UpdateLocationDto dto);

	void deleteById(Long locationId);

	List<ListOfCityDto> countHotelByLocationId();

}
