package nhom8.javabackend.hotel.hotel.entity;

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
@Table(name = "hotel_location")
public class Location extends BaseEntity {
	
	private int lat;
	
	private int lng;
	
	private String formattedAddress;
	
	private String zipcode;
	
	private String city;
	
	private String stateLong;
	
	private String stateShort;
	
	private String countryLong;
	
	private String countryShort;
	
	private int numberOfPost;
}
