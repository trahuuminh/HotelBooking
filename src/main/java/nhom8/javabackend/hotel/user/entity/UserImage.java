package nhom8.javabackend.hotel.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nhom8.javabackend.hotel.common.entity.BaseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_image")
public class UserImage extends BaseEntity{

	private String name;
	
	private String url;
}
