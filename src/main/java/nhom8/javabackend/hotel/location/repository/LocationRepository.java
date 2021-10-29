package nhom8.javabackend.hotel.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.location.dto.ListOfCityDto;
import nhom8.javabackend.hotel.location.dto.LocationDto;
import nhom8.javabackend.hotel.location.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT l FROM Location l")
	List<LocationDto> findAllDto();

	
	@Transactional(readOnly = true)
	@Query("SELECT l.id as id, l.city as city, COUNT(h.location) as numberOfHotel "
			+ "FROM Location l LEFT JOIN Hotel h ON h.location = l.id "
			+ "GROUP BY l.id")
	List<ListOfCityDto> countHotelByLocationId();

	

}

