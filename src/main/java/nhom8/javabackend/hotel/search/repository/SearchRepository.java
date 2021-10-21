package nhom8.javabackend.hotel.search.repository;

import java.util.List;
import java.util.Optional;

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
	@Query("SELECT h FROM Hotel h, Amenities am, Location l WHERE h.amenities = am.id AND h.location = l.id "
			+ "AND (LOWER(l.city) LIKE %:keyword% OR :keyword IS NULL) "
			+ "AND (am.wifiAvailability = :wifiAvailability OR :wifiAvailability IS NULL) "
			+ "AND (am.parkingAvailability = :parkingAvailability OR :parkingAvailability IS NULL) "
			+ "AND (am.poolAvailability = :poolAvailability OR :poolAvailability IS NULL) "
			+ "AND (am.airCondition = :airCondition OR :airCondition IS NULL) "
			+ "AND (am.extraBedFacility = :extraBedFacility OR :extraBedFacility IS NULL) "
			+ "AND (cast(h.price as int) >= :lowPrice OR :lowPrice IS NULL) "
			+ "AND (cast(h.price as int) <= :highPrice OR :highPrice IS NULL) "
			+ "AND (am.guestRoom = :guestRoom OR :guestRoom IS NULL) "
			+ "AND (am.bedRoom = :bedRoom OR :bedRoom IS NULL)")
	List<HotelDto> findHotelByOption(@Param("keyword") Optional<String> keyword, 
									@Param("wifiAvailability") Optional<String> wifiAvailability, 
									@Param("parkingAvailability") Optional<String> parkingAvailability, 
									@Param("poolAvailability") Optional<String> poolAvailability, 
									@Param("airCondition") Optional<String> airCondition, 
									@Param("extraBedFacility") Optional<String> extraBedFacility, 
									@Param("lowPrice") Optional<Integer> lowPrice, 
									@Param("highPrice") Optional<Integer> highPrice, 
									@Param("guestRoom") Optional<Integer> guestRoom, 
									@Param("bedRoom") Optional<Integer> bedRoom);

}
