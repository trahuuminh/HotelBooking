package nhom8.javabackend.hotel.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

@Repository
public interface SearchRepository extends JpaRepository<Hotel, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h, Location l WHERE h.location = l.id AND l.city LIKE %:keyword%")
	List<HotelDto> findHotelByKeyword(@Param("keyword") String keyword);

	
	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h, Location l WHERE h.location = l.id")
	List<HotelDto> findAllDto();


	@Transactional(readOnly = true)
	@Query("SELECT h FROM Hotel h, Amenities am WHERE h.amenities = am.id "
			+ "AND am.wifiAvailability = :wifiAvailability "
			+ "AND am.parkingAvailability = :parkingAvailability "
			+ "AND am.poolAvailability = :poolAvailability "
			+ "AND am.airCondition = :airCondition "
			+ "AND am.extraBedFacility = :extraBedFacility "
			+ "AND cast(h.price as int) >= cast( :lowPrice as int) "
			+ "AND cast(h.price as int) <= cast( :highPrice as int) "
			+ "AND am.guestRoom = :guestRoom "
			+ "AND am.bedRoom = :bedRoom")
	List<HotelDto> findHotelByOption(@Param("wifiAvailability") boolean wifiAvailability, 
									@Param("parkingAvailability") boolean parkingAvailability, 
									@Param("poolAvailability") boolean poolAvailability, 
									@Param("airCondition") boolean airCondition, 
									@Param("extraBedFacility") boolean extraBedFacility, 
									@Param("lowPrice") String lowPrice, 
									@Param("highPrice") String highPrice, 
									@Param("guestRoom") int guestRoom, 
									@Param("bedRoom") int bedRoom);

}
