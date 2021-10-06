package nhom8.javabackend.hotel.user.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.user.dto.userimage.CreateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UpdateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;
import nhom8.javabackend.hotel.user.entity.UserImage;

public interface UserImageService {
	List<UserImageDto> findAllUserImageDto();
	
	UserImage createNewUserImage(CreateUserImageDto dto);
	
	UserImage updateUserImage(UpdateUserImageDto dto);
	
	void deleteUserImage(Long id);
}
