package nhom8.javabackend.hotel.booking.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nhom8.javabackend.hotel.common.entity.BaseEntity;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.user.entity.User;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"hotel","customer"})
@EqualsAndHashCode(callSuper = false, exclude = {"hotel","customer"})
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity{
	@NotNull
	private String bookerEmail;
	
	private String bookerContact;
	
	private String message;
	
	@NotNull
	private int rooms;
	
	@NotNull
	private int guests;
	
	@NotNull
	private LocalDateTime startDate;
	
	@NotNull
	private LocalDateTime endDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
}
