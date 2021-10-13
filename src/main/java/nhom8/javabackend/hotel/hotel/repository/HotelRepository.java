package nhom8.javabackend.hotel.hotel.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h")
	Page<HotelDto> findAllDto(Pageable pageable);

}
