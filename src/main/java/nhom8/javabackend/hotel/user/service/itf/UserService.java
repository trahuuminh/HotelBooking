package nhom8.javabackend.hotel.user.service.itf;

import java.util.List;

import javax.validation.Valid;

import nhom8.javabackend.hotel.user.dto.AddHotelDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserService {
	List<UserDto> findAllUser();
	
	User createUser(CreateUserDto dto);
	
	User updateUser(UpdateUserDto dto);
	
	User getUserDetails(Long id);
	
	void deleteUser(Long id);

	boolean isExistedId(Long userId);

	User addHotel(AddHotelDto dto);

	User removeHotel(@Valid AddHotelDto dto);
}
