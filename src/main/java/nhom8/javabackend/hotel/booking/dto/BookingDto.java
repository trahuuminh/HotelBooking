package nhom8.javabackend.hotel.booking.dto;

import java.time.LocalDateTime;

public interface BookingDto {

	public Long getId();

	public String getBookerEmail();

	public String getBookerContact();

	public String getMessage();

	public int getRooms();

	public int getGuests();

	public LocalDateTime getStartDate();

	public LocalDateTime getEndDate();
}
