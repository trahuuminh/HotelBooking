package nhom8.javabackend.hotel.user.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.user.dto.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserService {
	List<UserDto> findAllUserDto();
	
	User createUser(CreateUserDto dto);
	
	User updateUser(UpdateUserDto dto);
	
	void deleteUser(Long id);
}
