package nhom8.javabackend.hotel.user.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.user.dto.AddHotelDto;
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
	private HotelRepository hotelRepo;
	private PasswordEncoder encode;
	public UserServiceImpl(UserRepository userRepository, HotelRepository hotelRepository, PasswordEncoder encoder) {
		userRepo=userRepository;
		hotelRepo=hotelRepository;
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
	public boolean isExistedId(Long userId) {
		return userRepo.existsById(userId);
	}

	@Override
	public User addHotel(AddHotelDto dto) {
		User user = userRepo.getById(dto.getUserId());
		Hotel hotel = hotelRepo.getById(dto.getHotelId());
		
		user.addHotel(hotel);
		
		return userRepo.save(user);
	}

	@Override
	public User removeHotel(@Valid AddHotelDto dto) {
		User user = userRepo.getById(dto.getUserId()); 
		Hotel hotel = hotelRepo.getById(dto.getHotelId());
		
		user.removeHotel(hotel);
		
		return userRepo.save(user);
	}
	
	public User getUserDetails(Long id) {
		if(userRepo.countById(id)==0)
			return null;
		
		return userRepo.getUserById(id);
	}
	
	
}
