package nhom8.javabackend.hotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "amentities")
public class Amenities extends BaseEntity {
	
	@NotNull
	private int guestRoom;
	
	@NotNull
	private int bedRoom;
	
	@NotNull
	private boolean wifiAvailability;
	
	@NotNull
	private boolean parkingAvailability;
	
	@NotNull
	private boolean poolAvailability;
	
	@NotNull
	private boolean airCondition;
	
	@NotNull
	private boolean extraBedFacility;
}
