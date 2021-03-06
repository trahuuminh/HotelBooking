package nhom8.javabackend.hotel.user.service.impl;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.booking.repository.BookingRepository;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.review.repository.ReviewRepository;
import nhom8.javabackend.hotel.security.dto.RegisterDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.PagingFormatUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.entity.UserImage;
import nhom8.javabackend.hotel.user.repository.UserRepository;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	private HotelRepository hotelRepo;
	private PasswordEncoder encode;
	private BookingRepository bookingRepo;
	private ReviewRepository reviewRepo;
	
	public UserServiceImpl(UserRepository userRepository, HotelRepository hotelRepository, PasswordEncoder encoder, BookingRepository bookingRepository,ReviewRepository reviewRepository) {
		
		userRepo=userRepository;
		hotelRepo=hotelRepository;
		encode=encoder;
		bookingRepo=bookingRepository;
		reviewRepo=reviewRepository;
	}
	
	@Transactional
	@Override
	public Page<UserDto> findAllUser(Pageable pageable) {
		return userRepo.findAllUser(pageable);
	}

	@Override
	public User createUser(CreateUserDto dto) {
		User user=new User();
		
		user.setRole(dto.getRole());
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
		
		if(dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
		if(dto.getLastName() != null) user.setLastName(dto.getLastName());
		if(dto.getPassword() != null) user.setPassword(encode.encode(dto.getPassword()));
		if(dto.getEmail() != null) user.setEmail(dto.getEmail());
		if(dto.getCellNumber() != null) user.setCellNumber(dto.getCellNumber());
		if(dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
		if(dto.getGender() != null) user.setGender(dto.getGender());
		if(dto.getContent() != null) user.setContent(dto.getContent());
		if(dto.getLanguage() != null) user.setLanguage(dto.getLanguage());
		if(dto.getFacebook() != null) user.setFacebook(dto.getFacebook());
		if(dto.getTwitter() != null) user.setTwitter(dto.getTwitter());
		if(dto.getLinkedin() != null) user.setLinkedin(dto.getLinkedin());
		if(dto.getInstagram() != null) user.setInstagram(dto.getInstagram());
		if(dto.getPinterest() != null) user.setPinterest(dto.getPinterest());
		
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		
		User user=userRepo.getById(userId);
		for(Hotel h: user.getListedPost()) {
			h.setAgent(null);
			h.getBookings().clear();
			hotelRepo.save(h);
		}
		
		for(Booking b: user.getBookings()) {
			b.setCustomer(null);
			b.setHotel(null);
			bookingRepo.save(b);
		}
		
		for(Review r: user.getReviews()) {
			r.setAuthor(null);
			r.setHotel(null);
			reviewRepo.save(r);
		}
		
		for(Hotel hotel: user.getFavouritePost()) {
			hotel.removeFavouriteUser(user);
		}
		
		
	}

	@Override
	public boolean isExistedId(Long userId) {
		return userRepo.existsById(userId);	
	}

	@Override
	public User addFavouriteHotel(Long hotelId, User user) {
		Hotel hotel = hotelRepo.getById(hotelId);
		
		user.addFavouriteHotel(hotel);
		
		return userRepo.save(user);
	}

	@Override
	public User removeFavouriteHotel(Long hotelId, User user) {
		Hotel hotel = hotelRepo.getById(hotelId);
		
		user.removeFavouriteHotel(hotel);
		
		return userRepo.save(user);
	}
	
	@Transactional
	@Override
	public UserDto getUserDetails(Long id) {
		if(userRepo.countById(id)==0)
			return null;
		
		return userRepo.getUserById(id);
	}

	@Override
	public PagingFormatUserDto pagingFormat(Page<UserDto> page) {
		PagingFormatUserDto dto=new PagingFormatUserDto();
		
		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());
		
		return dto;
	}
	
	@Transactional
	@Override
	public UserDto getUserDtoByUsername(String username) {
		return userRepo.getUserDtoByUsername(username);
	}

	@Transactional
	@Override
	public User getUserByEmail(String email) {
		return userRepo.getByEmail(email);
	}

	@Transactional
	@Override
	public User getUserByUsername(String username) {
		return userRepo.getByUsername(username);
	}

	@Override
	public User setUserProfilePic(User user, UserImage userImage) {
		user.setProfilePic(userImage);
		return userRepo.save(user);
	}
	
	@Override
	public User setUserCoverPic(User user, UserImage userImage) {
		user.setCoverPic(userImage);
		return userRepo.save(user);
	}

	@Override
	public User getUserByUserId(Long userId) {
		return userRepo.getById(userId);
	}

	@Override
	public User register(RegisterDto dto) {
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
}
