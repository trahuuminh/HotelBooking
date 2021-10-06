package nhom8.javabackend.hotel.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.user.dto.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u")
	List<UserDto> findAllUserDto();
}
