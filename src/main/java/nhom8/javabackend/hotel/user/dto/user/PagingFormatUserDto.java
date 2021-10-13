package nhom8.javabackend.hotel.user.dto.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingFormatUserDto {

	private int pageNumber;
	
	private int pageSize;
	
	private Long totalRecordCount;
	
	private List<UserDto>records;
}
