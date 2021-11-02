package nhom8.javabackend.hotel.booking.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.PagingFormatBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.booking.repository.BookingRepository;
import nhom8.javabackend.hotel.booking.service.itf.BookingService;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.user.entity.User;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	private BookingRepository repository;
	private HotelRepository hotelRepo;
	
	public BookingServiceImpl(BookingRepository bookingRepository,HotelRepository hotelRepository) {
		repository = bookingRepository;
		hotelRepo=hotelRepository;
	}

	@Override
	public Page<BookingDto> findAllBooking(Pageable pageable) {
		return repository.findAllBooking(pageable);
	}

	@Override
	public Booking addedNewBooking(CreateBookingDto dto, User customer) {
		Booking newBooking = new Booking();
		Hotel hotel=hotelRepo.getById(dto.getHotelId());
		
		newBooking.setCustomer(customer);
		newBooking.setHotel(hotel);
		newBooking.setBookerEmail(dto.getBookerEmail());
		newBooking.setBookerContact(dto.getBookerEmail());
		newBooking.setMessage(dto.getMessage());
		newBooking.setRooms(dto.getRooms());
		newBooking.setGuests(dto.getGuests());
		newBooking.setStartDate(dto.getStartDate());
		newBooking.setEndDate(dto.getEndDate());

		return repository.save(newBooking);
	}

	@Override
	public Booking updateBooking(UpdateBookingDto dto) {
		Booking updateBooking = repository.getById(dto.getId());

		if(dto.getBookerEmail() != null) updateBooking.setBookerEmail(dto.getBookerEmail());
		if(dto.getBookerContact() != null) updateBooking.setBookerContact(dto.getBookerContact());
		if(dto.getMessage() != null) updateBooking.setMessage(dto.getMessage());
		if(dto.getRooms() != 0) updateBooking.setRooms(dto.getRooms());
		if(dto.getGuests() != 0) updateBooking.setGuests(dto.getGuests());
		if(dto.getStartDate() != null) updateBooking.setStartDate(dto.getStartDate());
		if(dto.getEndDate() != null) updateBooking.setEndDate(dto.getEndDate());
		
		return repository.save(updateBooking);
	}

	@Override
	public void deleteById(Long bookingId) {
		repository.deleteById(bookingId);;

	}

	@Override
	public boolean isExistedId(long bookingId) {
		return repository.existsById(bookingId);
	}

	@Override
	public PagingFormatBookingDto pagingFormat(Page<BookingDto> page) {
		PagingFormatBookingDto dto=new PagingFormatBookingDto();
		
		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());
		
		return dto;
	}

	@Override
	public Page<BookingDto> findAllBookingByAgentId(Long agentId, Pageable pageable) {
		return repository.findAllBookingByAgentId(agentId, pageable);
	}

	@Override
	public Booking getBookingByBookingId(Long bookingId) {
		return repository.getById(bookingId);
	}

	
}
