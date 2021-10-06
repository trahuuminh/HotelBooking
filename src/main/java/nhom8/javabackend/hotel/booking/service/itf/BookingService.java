package nhom8.javabackend.hotel.booking.service.itf;

import java.util.List;

import javax.validation.Valid;

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;

public interface BookingService {

	List<BookingDto> findAllDto();

	Booking addedNewBooking(@Valid CreateBookingDto dto);

	Booking updateBooking(@Valid UpdateBookingDto dto);

	void deleteById(Long bookingId);

}
