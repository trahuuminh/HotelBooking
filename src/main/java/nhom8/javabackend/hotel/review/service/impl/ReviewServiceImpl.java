package nhom8.javabackend.hotel.review.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.review.dto.CreateReviewDto;
import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.dto.UpdateReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.review.repository.ReviewRepository;
import nhom8.javabackend.hotel.review.service.itf.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	private ReviewRepository reviewRepo;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		reviewRepo=reviewRepository;
	}
	
	@Override
	public List<ReviewDto> findAllReviewDto() {
		return reviewRepo.findAllReviewDto();
	}

	@Override
	public Review createNewReview(CreateReviewDto dto) {
		Review review =new Review();
		
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
	
	
}
