package nhom8.javabackend.hotel.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;
import nhom8.javabackend.hotel.user.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
	
	@Query("SELECT ui FROM UserImage ui")
	List<UserImageDto> findAllUserImageDto();
	
}
