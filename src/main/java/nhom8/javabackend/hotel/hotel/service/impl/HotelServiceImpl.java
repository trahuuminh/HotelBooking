package nhom8.javabackend.hotel.hotel.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	private HotelRepository repository;

	public HotelServiceImpl(HotelRepository hotelRepository) {
		repository = hotelRepository;
	}

	@Override
	public List<HotelDto> findAllDto() {
		return repository.findAllDto();
	}

	@Override
	public Hotel addNewHotel(@Valid CreateHotelDto dto) {

		Hotel newHotel = new Hotel();

		newHotel.setTitle(dto.getTitle());
		newHotel.setSlug(dto.getSlug());
		newHotel.setContent(dto.getContent());
		newHotel.setStatus(dto.getStatus());
		newHotel.setPrice(dto.getPrice());
		newHotel.setNegotiable(dto.isNegotiable());
		newHotel.setPropertyType(dto.getPropertyType());
		newHotel.setCondition(dto.getCondition());
		newHotel.setRating(dto.getRating());
		newHotel.setRatingCount(dto.getRatingCount());
		newHotel.setContactNumber(dto.getContactNumber());
		newHotel.setTermsAndCondition(dto.getTermsAndCondition());

		return repository.save(newHotel);
	}

	@Override
	public Hotel updateHotel(@Valid UpdateHotelDto dto) {

		Hotel updateHotel = repository.getById(dto.getId());

		updateHotel.setTitle(dto.getTitle());
		updateHotel.setSlug(dto.getSlug());
		updateHotel.setContent(dto.getContent());
		updateHotel.setStatus(dto.getStatus());
		updateHotel.setPrice(dto.getPrice());
		updateHotel.setNegotiable(dto.isNegotiable());
		updateHotel.setPropertyType(dto.getPropertyType());
		updateHotel.setCondition(dto.getCondition());
		updateHotel.setRating(dto.getRating());
		updateHotel.setRatingCount(dto.getRatingCount());
		updateHotel.setContactNumber(dto.getContactNumber());
		updateHotel.setTermsAndCondition(dto.getTermsAndCondition());

		return repository.save(updateHotel);
	}

	@Override
	public void deleteById(Long hotelId) {
		repository.deleteById(hotelId);
	}
}
