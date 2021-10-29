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
	@Query("SELECT l.id as id, l.lat as lat, l.lng as lng, l.formattedAddress as formattedAddress, "
			+ "l.zipcode as zipcode, l.city as city, l.stateLong as stateLong, l.stateShort as stateShort, "
			+ "l.countryLong as countryLong, l.countryShort as countryShort, l.numberOfPost as numberOfPost, COUNT(h.id) as countHotel "
			+ "FROM Location l LEFT JOIN Hotel h ON h.location = l.id "
			+ "GROUP BY l.id")
	List<LocationDto> findAllDto();

	

}

