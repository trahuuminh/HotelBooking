package nhom8.javabackend.hotel.user.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	UserDto getUserDtoByUsername(String username);
	
	int countById(Long id);
	
	@Query("SELECT u FROM User u WHERE u.id= ?1")
	UserDto getUserById(Long id);
	
	@Query("SELECT u FROM User u")
	Page<UserDto> findAllUser(Pageable pageable); 
	
	User getByEmail(String email);
}
