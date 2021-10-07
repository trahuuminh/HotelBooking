package nhom8.javabackend.hotel.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT b FROM Booking b")
	List<BookingDto> findAllDto();

}
