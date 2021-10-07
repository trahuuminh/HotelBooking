package nhom8.javabackend.hotel.hotel.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.AmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.CreateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;
import nhom8.javabackend.hotel.hotel.repository.AmenitiesRepository;
import nhom8.javabackend.hotel.hotel.service.itf.AmenitiesService;

@Service
@Transactional
public class AmenitiesServiceImpl implements AmenitiesService {

	private AmenitiesRepository repository;

	public AmenitiesServiceImpl(AmenitiesRepository amenitiesRepository) {
		repository = amenitiesRepository;
	}

	@Override
	public List<AmenitiesDto> findAllDto() {
		
		return repository.findAllDto();
	}

	@Override
	public Amenities addNewAmenities(@Valid CreateAmenitiesDto dto) {
		Amenities newAmenitie = new Amenities();

		newAmenitie.setGuestRoom(dto.getGuestRoom());
		newAmenitie.setBedRoom(dto.getBedRoom());
		newAmenitie.setWifiAvailability(dto.isWifiAvailability());
		newAmenitie.setParkingAvailability(dto.isParkingAvailability());
		newAmenitie.setPoolAvailability(dto.isPoolAvailability());
		newAmenitie.setAirCondition(dto.isAirCondition());
		newAmenitie.setExtraBedFacility(dto.isExtraBedFacility());

		return repository.save(newAmenitie);
	}

	@Override
	public Amenities updateAmenities(@Valid UpdateAmenitiesDto dto) {
		Amenities updateAmenitie = repository.getById(dto.getId());
		
		updateAmenitie.setGuestRoom(dto.getGuestRoom());
		updateAmenitie.setBedRoom(dto.getBedRoom());
		updateAmenitie.setWifiAvailability(dto.isWifiAvailability());
		updateAmenitie.setParkingAvailability(dto.isParkingAvailability());
		updateAmenitie.setPoolAvailability(dto.isPoolAvailability());
		updateAmenitie.setAirCondition(dto.isAirCondition());
		updateAmenitie.setExtraBedFacility(dto.isExtraBedFacility());

		return repository.save(updateAmenitie);
	}

	@Override
	public void deleteById(Long amenitiesId) {
		repository.deleteById(amenitiesId);;

	}

}
