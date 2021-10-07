package nhom8.javabackend.hotel.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "message")
public class Message extends BaseEntity{
	
	@NotNull
	@Email
	private String senderEmail;
	
	@NotNull
	private String senderContact;
	
	@NotNull
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private User agent;
}
