package nhom8.javabackend.hotel.review.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.review.dto.CreateReviewDto;
import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.dto.UpdateReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;

public interface ReviewService {
	
	List<ReviewDto> findAllReviewDto();
	
	Review createNewReview(CreateReviewDto dto);
	
	Review updateReview(UpdateReviewDto dto);
	
	void deleteReview(Long id);
}
