package nhom8.javabackend.hotel.hotel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.PagingFormatHotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.repository.AmenitiesRepository;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.location.entity.Location;
import nhom8.javabackend.hotel.location.repository.LocationRepository;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.review.repository.ReviewRepository;
import nhom8.javabackend.hotel.user.entity.User;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	private HotelRepository repository;
	private AmenitiesRepository amenRepo;
	private LocationRepository LocRepo;
	private ReviewRepository reviewRepo;

	public HotelServiceImpl(HotelRepository hotelRepository, AmenitiesRepository amenitieRepo,
			LocationRepository locationRepo, ReviewRepository reviewRepository) {
		repository = hotelRepository;
		amenRepo = amenitieRepo;
		LocRepo = locationRepo;
		reviewRepo = reviewRepository;
	}

	@Override
	public Page<HotelDto> findAllHotel(Pageable pageable) {
		return repository.findAllDto(pageable);
	}

	@Override
	public Page<HotelDto> FindHotelByMostBooking(Pageable pageable) {
		return repository.FindHotelByMostBooking(pageable);
	}

	@Override
	public Hotel addNewHotel(CreateHotelDto dto, User agent) {
		Amenities amen = amenRepo.getById(dto.getAmenitiesId());
		Location loc = LocRepo.getById(dto.getLocationId());
		Hotel newHotel = new Hotel();

		newHotel.setTitle(dto.getTitle());
		newHotel.setSlug(dto.getSlug());
		newHotel.setContent(dto.getContent() != null ? dto.getContent() : "");
		newHotel.setStatus(dto.getStatus());
		newHotel.setPrice(dto.getPrice());
		newHotel.setNegotiable(dto.isNegotiable());
		newHotel.setRating(0);
		newHotel.setCondition(dto.getCondition() != null ? dto.getCondition() : "");
		newHotel.setRatingCount(0);
		newHotel.setContactNumber(dto.getContactNumber() != null ? dto.getContactNumber() : "");
		newHotel.setTermsAndCondition(dto.getTermsAndCondition() != null ? dto.getTermsAndCondition() : "");
		newHotel.setAgent(agent);
		newHotel.setAmenities(amen);
		newHotel.setLocation(loc);

		return repository.save(newHotel);
	}

	@Override
	public Hotel updateHotel(UpdateHotelDto dto) {

		Hotel updateHotel = repository.getById(dto.getId());

		if(dto.getTitle() != null) updateHotel.setTitle(dto.getTitle());
		if(dto.getContent() != null) updateHotel.setContent(dto.getContent());
		if(dto.getStatus() != null) updateHotel.setStatus(dto.getStatus());
		if(dto.getPrice() != null) updateHotel.setPrice(dto.getPrice());
		if(dto.getCondition() != null) updateHotel.setCondition(dto.getCondition());
		if(dto.getContactNumber() != null) updateHotel.setContactNumber(dto.getContactNumber());
		if(dto.getTermsAndCondition() != null) updateHotel.setTermsAndCondition(dto.getTermsAndCondition());
		updateHotel.setNegotiable(dto.isNegotiable());

		return repository.save(updateHotel);
	}

	@Override
	public void deleteById(Long hotelId) {

		Hotel hotel = repository.getById(hotelId);

		for (Review r : hotel.getReviews()) {
			r.setAuthor(null);
			r.setHotel(null);
			reviewRepo.save(r);
		}

		hotel.getAgent().getListedPost().remove(hotel);

		for(User user: hotel.getUsersFavourite()) {
			user.removeFavouriteHotel(hotel);
		}

	}

	@Override
	public boolean isExistedId(Long hotelId) {
		return repository.existsById(hotelId);
	}

	@Override
	public PagingFormatHotelDto pagingFormat(Page<HotelDto> page) {
		PagingFormatHotelDto dto = new PagingFormatHotelDto();

		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());

		return dto;
	}

	@Override
	public Hotel getHotelByHotelId(Long hotelId) {
		return repository.getById(hotelId);
	}

	@Override
	public Hotel setHotelCoverPic(Hotel hotel, HotelImages image) {
		hotel.setCoverPic(image);
		return repository.save(hotel);
	}

	@Override
	public HotelDto getHotelBySlugName(String slug) {
		return repository.getOneBySlug(slug);
	}

}
