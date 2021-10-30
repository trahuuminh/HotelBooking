package nhom8.javabackend.hotel.location.entity;

import javax.persistence.Column;
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
@Table(name = "location")
public class Location extends BaseEntity {
	
	private float lat;
	
	private float lng;
	
	private String formattedAddress;
	
	private String zipcode;
	
	@Column(unique = true)
	private String city;
	
	private String stateLong;
	
	private String stateShort;
	
	private String countryLong;
	
	private String countryShort;
	
	private int numberOfPost;
}
