package nhom8.javabackend.hotel.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;

public interface HotelImagesRepository extends JpaRepository<HotelImages, Long> {

	@Query("SELECT hi FROM HotelImages hi")
	List<HotelImagesDto> findAllHotelImagesDto();
	
	@Query("SELECT hi.id FROM HotelImages hi JOIN hi.hotel h WHERE h.id = ?1")
	List<Long> findAllHotelImagesIdByHotelId(Long hotelId);
}
