package nhom8.javabackend.hotel.booking.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.booking.repository.BookingRepository;
import nhom8.javabackend.hotel.booking.service.itf.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	private BookingRepository repository;

	public BookingServiceImpl(BookingRepository bookingRepository) {
		repository = bookingRepository;
	}

	@Override
	public List<BookingDto> findAllDto() {
		return repository.findAllDto();
	}

	@Override
	public Booking addedNewBooking(@Valid CreateBookingDto dto) {
		Booking newBooking = new Booking();

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
	public Booking updateBooking(@Valid UpdateBookingDto dto) {
		Booking updateBooking = repository.getById(dto.getId());

		updateBooking.setBookerEmail(dto.getBookerEmail());
		updateBooking.setBookerContact(dto.getBookerEmail());
		updateBooking.setMessage(dto.getMessage());
		updateBooking.setRooms(dto.getRooms());
		updateBooking.setGuests(dto.getGuests());
		updateBooking.setStartDate(dto.getStartDate());
		updateBooking.setEndDate(dto.getEndDate());
		
		return repository.save(updateBooking);
	}

	@Override
	public void deleteById(Long bookingId) {
		repository.deleteById(bookingId);;

	}
}
