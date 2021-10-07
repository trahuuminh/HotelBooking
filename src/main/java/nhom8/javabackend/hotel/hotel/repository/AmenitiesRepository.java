package nhom8.javabackend.hotel.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.AmenitiesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT am FROM Amenities am")
	List<AmenitiesDto> findAllDto();

}
