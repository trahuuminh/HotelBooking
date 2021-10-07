package nhom8.javabackend.hotel.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	@Query("SELECT r FROM Review r")
	List<ReviewDto> findAllReviewDto();
}
