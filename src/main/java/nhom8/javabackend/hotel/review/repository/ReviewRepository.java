package nhom8.javabackend.hotel.review.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	@Query("SELECT r FROM Review r")
	Page<ReviewDto> findAllReviewDto(Pageable pageable);
	
	@Query("SELECT r.id FROM Review r JOIN r.hotel h WHERE h.id = ?1")
	List<Long> findAllReviewIdByHotelId(Long hotelId);
	
	@Query("SELECT r.id FROM Review r JOIN r.author a WHERE a.id = ?1 ")
	List<Long>findAllReviewIdByAuthorId(Long authorId);
}
