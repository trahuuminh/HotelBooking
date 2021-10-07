package nhom8.javabackend.hotel.location.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.location.dto.CreateLocationDto;
import nhom8.javabackend.hotel.location.dto.LocationDto;
import nhom8.javabackend.hotel.location.dto.UpdateLocationDto;
import nhom8.javabackend.hotel.location.entity.Location;
import nhom8.javabackend.hotel.location.repository.LocationRepository;
import nhom8.javabackend.hotel.location.service.itf.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private LocationRepository repository;

	public LocationServiceImpl(LocationRepository locationRepository) {
		repository = locationRepository;
	}

	@Override
	public List<LocationDto> findAllDto() {
		return repository.findAllDto();
	}

	@Override
	public Location addNewLocation(@Valid CreateLocationDto dto) {
		Location newLocation = new Location();

		newLocation.setLat(dto.getLat());
		newLocation.setLng(dto.getLng());
		newLocation.setFormattedAddress(dto.getFormattedAddress());
		newLocation.setZipcode(dto.getZipcode());
		newLocation.setCity(dto.getCity());
		newLocation.setStateLong(dto.getStateLong());
		newLocation.setStateShort(dto.getStateShort());
		newLocation.setCountryLong(dto.getCountryLong());
		newLocation.setCountryShort(dto.getCountryShort());
		newLocation.setNumberOfPost(dto.getNumberOfPost());

		return repository.save(newLocation);
	}

	@Override
	public Location updateLocation(@Valid UpdateLocationDto dto) {
		Location updateLocation = repository.getById(dto.getId());

		updateLocation.setLat(dto.getLat());
		updateLocation.setLng(dto.getLng());
		updateLocation.setFormattedAddress(dto.getFormattedAddress());
		updateLocation.setZipcode(dto.getZipcode());
		updateLocation.setCity(dto.getCity());
		updateLocation.setStateLong(dto.getStateLong());
		updateLocation.setStateShort(dto.getStateShort());
		updateLocation.setCountryLong(dto.getCountryLong());
		updateLocation.setCountryShort(dto.getCountryShort());
		updateLocation.setNumberOfPost(dto.getNumberOfPost());
		
		return repository.save(updateLocation);
	}

	@Override
	public void deleteById(Long locationId) {
		repository.deleteById(locationId);;

	}

}
