package nhom8.javabackend.hotel.user.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.repository.UserRepository;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	private PasswordEncoder encode;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
		userRepo=userRepository;
		encode=encoder;
	}
	
	@Override
	public List<UserDto> findAllUser() {
		return userRepo.findAllUser();
	}

	@Override
	public User createUser(CreateUserDto dto) {
		User user=new User();
		
		user.setRole(Role.USER);
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setUsername(dto.getUsername());
		user.setPassword(encode.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setCellNumber(dto.getCellNumber());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setGender(dto.getGender());
		user.setContent(dto.getContent());
		user.setLanguage(dto.getLanguage());
		user.setFacebook(dto.getFacebook());
		user.setTwitter(dto.getTwitter());
		user.setLinkedin(dto.getLinkedin());
		user.setInstagram(dto.getInstagram());
		user.setPinterest(dto.getPinterest());;
		
		return userRepo.save(user);
	}

	@Override
	public User updateUser(UpdateUserDto dto) {
		User user=userRepo.getById(dto.getId());
		
		user.setRole(dto.getRole());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setCellNumber(dto.getCellNumber());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setGender(dto.getGender());
		user.setContent(dto.getContent());
		user.setLanguage(dto.getLanguage());
		user.setFacebook(dto.getFacebook());
		user.setTwitter(dto.getTwitter());
		user.setLinkedin(dto.getLinkedin());
		user.setInstagram(dto.getInstagram());
		user.setPinterest(dto.getPinterest());
		
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		
	}

	@Override
	public User getUserDetails(Long id) {
		if(userRepo.countById(id)==0)
			return null;
		
		return userRepo.getUserById(id);
	}
	
	
}
