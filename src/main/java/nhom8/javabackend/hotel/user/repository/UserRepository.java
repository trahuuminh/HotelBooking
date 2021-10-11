package nhom8.javabackend.hotel.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	
	int countById(Long id);
	
	@Query("SELECT u FROM User u WHERE u.id= ?1")
	User getUserById(Long id);
	
	@Query("SELECT u FROM User u")
	List<UserDto> findAllUser();

	
}
