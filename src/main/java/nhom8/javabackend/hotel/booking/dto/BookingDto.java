package nhom8.javabackend.hotel.booking.dto;

import java.time.LocalDateTime;

import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.user.entity.User;

public interface BookingDto {

	public Long getId();

	public String getBookerEmail();

	public String getBookerContact();

	public String getMessage();

	public int getRooms();

	public int getGuests();

	public LocalDateTime getStartDate();

	public LocalDateTime getEndDate();
	
	public User getCustomer();
	
	public Hotel getHotel();
}
