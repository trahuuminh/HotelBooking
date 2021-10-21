package nhom8.javabackend.hotel.user.dto.userimage;

import lombok.Data;

@Data
public class CreateUserImageDto {

	private String name;
	
	private String url;
	
	public CreateUserImageDto(String name, String url) {
		this.name=name;
		this.url=url;
	}
}
