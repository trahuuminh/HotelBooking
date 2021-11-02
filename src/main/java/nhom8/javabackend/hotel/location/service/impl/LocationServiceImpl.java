package nhom8.javabackend.hotel.location.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.location.dto.CreateLocationDto;
import nhom8.javabackend.hotel.location.dto.ListOfCityDto;
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
		newLocation.setCity(dto.getCity().equals("") ? dto.getCountryLong() : dto.getCity());
		newLocation.setStateLong(dto.getStateLong());
		newLocation.setStateShort(dto.getStateShort());
		newLocation.setCountryLong(dto.getCountryLong());
		newLocation.setCountryShort(dto.getCountryShort());
		newLocation.setNumberOfPost(dto.getNumberOfPost());

		return repository.save(newLocation);
	}

	@Override
	public Location updateLocation(UpdateLocationDto dto) {
		Location updateLocation = repository.getById(dto.getId());

		if(dto.getLat() != 0) updateLocation.setLat(dto.getLat());
		if(dto.getLng() != 0) updateLocation.setLng(dto.getLng());
		if(dto.getFormattedAddress() != null) updateLocation.setFormattedAddress(dto.getFormattedAddress());
		if(dto.getZipcode() != null) updateLocation.setZipcode(dto.getZipcode());
		if(dto.getCity() != null) updateLocation.setCity(dto.getCity());
		if(dto.getStateLong() != null) updateLocation.setStateLong(dto.getStateLong());
		if(dto.getStateShort() != null) updateLocation.setStateShort(dto.getStateShort());
		if(dto.getCountryLong() != null) updateLocation.setCountryLong(dto.getCountryLong());
		if(dto.getCountryShort() != null) updateLocation.setCountryShort(dto.getCountryShort());
		
		return repository.save(updateLocation);
	}

	@Override
	public void deleteById(Long locationId) {
		repository.deleteById(locationId);;

	}

	@Override
	public List<ListOfCityDto> countHotelByLocationId() {
		return repository.countHotelByLocationId();
	}

}
