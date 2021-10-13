package nhom8.javabackend.hotel.review.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingFormatReviewDto {
	
private int pageNumber;
	
	private int pageSize;
	
	private Long totalRecordCount;
	
	private List<ReviewDto>records;
}
