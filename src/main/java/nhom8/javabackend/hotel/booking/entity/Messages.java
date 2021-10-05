package nhom8.javabackend.hotel.booking.entity;

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
import nhom8.javabackend.hotel.user.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "messages")
public class Messages extends BaseEntity{
	
	@NotNull
	private String senderEmail;
	
	@NotNull
	private String senderContact;
	
	@NotNull
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private User agent;
}
