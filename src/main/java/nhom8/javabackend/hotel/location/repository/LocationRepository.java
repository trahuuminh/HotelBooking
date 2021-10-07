package nhom8.javabackend.hotel.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.location.dto.LocationDto;
import nhom8.javabackend.hotel.location.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT l FROM Location l")
	List<LocationDto> findAllDto();

}
