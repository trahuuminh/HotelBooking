package nhom8.javabackend.hotel.user.dto.userimage;

import lombok.Data;

@Data
public class CreateUserImageDto {

	private String path;
	
	private String url;
	
	public CreateUserImageDto(String path, String url) {
		this.path=path;
		this.url=url;
	}
}
