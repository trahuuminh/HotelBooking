package nhom8.javabackend.hotel.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.user.dto.userimage.CreateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UpdateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;
import nhom8.javabackend.hotel.user.entity.UserImage;
import nhom8.javabackend.hotel.user.repository.UserImageRepository;
import nhom8.javabackend.hotel.user.service.itf.UserImageService;

@Service
public class UserImageServiceImpl implements UserImageService {
	
	private UserImageRepository userImageRepo;
	
	public UserImageServiceImpl(UserImageRepository userImageRepository) {
		userImageRepo=userImageRepository;
	}

	@Override
	public List<UserImageDto> findAllUserImageDto() {
		return userImageRepo.findAllUserImageDto();
	}

	@Override
	public UserImage createNewUserImage(CreateUserImageDto dto) {
		UserImage userImage=new UserImage();
		
		userImage.setPath(dto.getPath());
		userImage.setUrl(dto.getUrl());
		
		return userImageRepo.save(userImage);
	}

	@Override
	public UserImage updateUserImage(UpdateUserImageDto dto) {
		UserImage userImage=userImageRepo.getById(dto.getId());
		
		userImage.setPath(dto.getName());
		userImage.setUrl(dto.getUrl());
		
		return userImageRepo.save(userImage);
	}

	@Override
	public void deleteUserImage(Long id) {
		userImageRepo.deleteById(id);	
	}

	@Override
	public boolean isExistedId(Long id) {
		return userImageRepo.existsById(id);
	}
	
}
