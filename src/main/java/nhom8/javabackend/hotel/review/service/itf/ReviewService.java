package nhom8.javabackend.hotel.review.service.itf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.review.dto.CreateReviewDto;
import nhom8.javabackend.hotel.review.dto.PagingFormatReviewDto;
import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.dto.UpdateReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.user.entity.User;

public interface ReviewService {
	
	Page<ReviewDto> findAllReviewDto(Pageable pageable);
	
	PagingFormatReviewDto pagingFormat(Page<ReviewDto> page);
	
	Review createNewReview(User author, CreateReviewDto dto);
	
	Review updateReview(UpdateReviewDto dto);
	
	void deleteReview(Long id);
	
	boolean isExistedId(Long id);
}
