package nhom8.javabackend.hotel.hotel.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingFormatHotelMostBookingDto {
	private int pageNumber;
	
	private int pageSize;
	
	private Long totalRecordCount;
	
	private List<FindHotelByMostBookingDto>records;
}
