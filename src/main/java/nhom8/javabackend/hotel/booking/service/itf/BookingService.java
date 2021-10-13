package nhom8.javabackend.hotel.booking.service.itf;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.PagingFormatBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;

public interface BookingService {

	Page<BookingDto> findAllBooking(Pageable pageable);
	
	PagingFormatBookingDto pagingFormat(Page<BookingDto> page);

	Booking addedNewBooking(@Valid CreateBookingDto dto);

	Booking updateBooking(@Valid UpdateBookingDto dto);

	void deleteById(Long bookingId);
	
	boolean isExistedId(long bookingId);

}
