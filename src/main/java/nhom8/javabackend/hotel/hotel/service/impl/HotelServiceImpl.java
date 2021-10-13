package nhom8.javabackend.hotel.hotel.service.impl;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.PagingFormatHotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.repository.UserRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	private HotelRepository repository;
	private UserRepository userRepo;

	public HotelServiceImpl(HotelRepository hotelRepository, UserRepository userRepository) {
		repository = hotelRepository;
		userRepo=userRepository;
	}

	@Override
	public Page<HotelDto> findAllHotel(Pageable pageable) {
		return repository.findAllDto(pageable);
	}

	@Override
	public Hotel addNewHotel(@Valid CreateHotelDto dto) {
		User agent=userRepo.getById(dto.getAgentId());
		Hotel newHotel = new Hotel();

		newHotel.setTitle(dto.getTitle());
		newHotel.setSlug(dto.getSlug());
		newHotel.setContent(dto.getContent());
		newHotel.setStatus(dto.getStatus());
		newHotel.setPrice(dto.getPrice());
		newHotel.setNegotiable(dto.isNegotiable());
		newHotel.setCondition(dto.getCondition());
		newHotel.setRating(dto.getRating());
		newHotel.setRatingCount(dto.getRatingCount());
		newHotel.setContactNumber(dto.getContactNumber());
		newHotel.setTermsAndCondition(dto.getTermsAndCondition());
		newHotel.setAgent(agent);
		
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

	@Override
	public boolean isExistedId(Long hotelId) {
		return repository.existsById(hotelId);
	}

	@Override
	public PagingFormatHotelDto pagingFormat(Page<HotelDto> page) {
		PagingFormatHotelDto dto=new PagingFormatHotelDto();
		
		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());
		
		return dto;
	}
}
