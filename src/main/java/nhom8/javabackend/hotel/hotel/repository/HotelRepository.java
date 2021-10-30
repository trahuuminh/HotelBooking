package nhom8.javabackend.hotel.hotel.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.FindHotelByMostBookingDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h")
	Page<HotelDto> findAllDto(Pageable pageable);

	@Query("SELECT h.id FROM Hotel h JOIN h.agent a WHERE a.id = ?1 ")
	List<Long>findAllHotelIdByAgentId(Long agentId);
	
	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h WHERE h.slug = ?1")
	HotelDto getOneBySlug(String slug);
	
	@Transactional(readOnly = true)
	@Query("SELECT h.id as id, h.title as title, h.slug as slug, h.content as content, "
			+ "h.status as status, h.price as price, h.isNegotiable as isNegotiable, "
			+ "h.condition as condition, h.rating as rating, h.ratingCount as ratingCount, "
			+ "h.contactNumber as contactNumber, h.termsAndCondition as termsAndCondition, "
			+ "h.agent as agent, h.amenities as amenities, h.location as location, "
			+ "h.coverPic as coverPic "
			+ "FROM Hotel h LEFT JOIN Booking b ON b.hotel = h.id ")
	Page<FindHotelByMostBookingDto> FindHotelByMostBooking(Pageable pageable);
}
