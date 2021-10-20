package nhom8.javabackend.hotel.user.service.itf;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.user.dto.AddHotelDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.PagingFormatUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface UserService  {
	Page<UserDto> findAllUser(Pageable pageable);
	
	PagingFormatUserDto pagingFormat(Page<UserDto> page);
	
	User createUser(CreateUserDto dto);
	
	User updateUser(UpdateUserDto dto);
	
	UserDto getUserDetails(Long id);
	
	void deleteUser(Long id);

	boolean isExistedId(Long userId);

	User addHotel(AddHotelDto dto);

	User removeHotel(@Valid AddHotelDto dto);
	
	UserDto getUserByUsername(String username);
	
	User getUserByEmail(String email);
}
