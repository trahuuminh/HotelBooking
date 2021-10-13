package nhom8.javabackend.hotel.booking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingFormatBookingDto {

private int pageNumber;
	
	private int pageSize;
	
	private Long totalRecordCount;
	
	private List<BookingDto>records;
}
