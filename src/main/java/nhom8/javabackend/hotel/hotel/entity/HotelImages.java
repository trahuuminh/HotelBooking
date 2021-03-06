package nhom8.javabackend.hotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(exclude = {"hotel"})
@EqualsAndHashCode(exclude = {"hotel"}, callSuper = false)
@Entity
@Table(name = "hotel_image")
public class HotelImages extends BaseEntity {
	
	@NotNull
	private String url ;
	
	@NotNull
	private String thumbUrl;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;
	
	public Long getHotelId() {
		if(this.hotel != null)
			return this.hotel.getId();
		return null;
	}
}
