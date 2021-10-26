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
import nhom8.javabackend.hotel.user.entity.UserImage;

public interface UserService  {
	Page<UserDto> findAllUser(Pageable pageable);
	
	PagingFormatUserDto pagingFormat(Page<UserDto> page);
	
	UserDto createUser(CreateUserDto dto);
	
	UserDto updateUser(UpdateUserDto dto);
	
	UserDto getUserDetails(Long id);
	
	void deleteUser(Long id);

	boolean isExistedId(Long userId);

	UserDto addFavouriteHotel(AddHotelDto dto, User user);

	UserDto removeFavouriteHotel(@Valid AddHotelDto dto,User user);
	
	UserDto getUserDtoByUsername(String username);
	
	User getUserByEmail(String email);
	
	User getUserByUsername(String username);
	
	UserDto setUserProfilePic(User user,UserImage userImage);
	
	UserDto setUserCoverPic(User user,UserImage userImage);
	
	User getUserByUserId(Long userId);
	
	User register(CreateUserDto dto);
}
