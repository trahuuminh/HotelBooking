package nhom8.javabackend.hotel.review.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.review.dto.CreateReviewDto;
import nhom8.javabackend.hotel.review.dto.PagingFormatReviewDto;
import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.dto.UpdateReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.review.repository.ReviewRepository;
import nhom8.javabackend.hotel.review.service.itf.ReviewService;
import nhom8.javabackend.hotel.user.entity.User;

@Service
public class ReviewServiceImpl implements ReviewService{

	private ReviewRepository reviewRepo;
	private HotelRepository hotelRepo;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository,HotelRepository hotelRepository) {
		reviewRepo=reviewRepository;
		hotelRepo=hotelRepository;
	}
	
	@Override
	public Page<ReviewDto> findAllReviewDto(Pageable pageable) {
		return reviewRepo.findAllReviewDto(pageable);
	}

	@Override
	public Review createNewReview(User author, CreateReviewDto dto) {
		Review review =new Review();
		Hotel hotel=hotelRepo.getById(dto.getHotelId());
		
		review.setAuthor(author);
		review.setHotel(hotel);
		review.setTitle(dto.getTitle());
		review.setText(dto.getText());
		review.setReviewDate(LocalDateTime.now());
		review.setServiceRating(dto.getServiceRating());
		review.setRoomRating(dto.getRoomRating());
		review.setCleannessRating(dto.getCleannessRating());
		review.setFoodRating(dto.getFoodRating());
		
		return reviewRepo.save(review);
	}

	@Override
	public Review updateReview(UpdateReviewDto dto) {
		Review review = reviewRepo.getById(dto.getId());
		
		review.setTitle(dto.getTitle());
		review.setText(dto.getText());
		review.setReviewDate(LocalDateTime.now());
		review.setServiceRating(dto.getServiceRating());
		review.setRoomRating(dto.getRoomRating());
		review.setCleannessRating(dto.getCleannessRating());
		review.setFoodRating(dto.getFoodRating());
		
		return reviewRepo.save(review);
	}

	@Override
	public void deleteReview(Long id) {
		reviewRepo.deleteById(id);
	}

	@Override
	public boolean isExistedId(Long id) {
		return reviewRepo.existsById(id);
	}

	@Override
	public PagingFormatReviewDto pagingFormat(Page<ReviewDto> page) {
		PagingFormatReviewDto dto=new PagingFormatReviewDto();
		
		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());
		
		return dto;
	}
	
	
	
}
