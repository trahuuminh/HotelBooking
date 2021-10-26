package nhom8.javabackend.hotel.user.dto.message;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingFormatMessageDto {

	private int pageNumber;
	
	private int pageSize;
	
	private Long totalRecordCount;
	
	List<MessageDto>records;
}
