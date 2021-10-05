package nhom8.javabackend.hotel.user.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.booking.entity.Messages;
import nhom8.javabackend.hotel.booking.entity.Review;
import nhom8.javabackend.hotel.common.entity.BaseEntity;
import nhom8.javabackend.hotel.hotel.entity.Hotel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"favouritePosts"})
@EqualsAndHashCode(exclude = {"favouritePosts"},callSuper = false)
@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@NotNull
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String email;
	
	@NotNull
	private String cellNumber;
	
	private String dateOfBirth;
	
	private String gender;
	
	private String content;
	
	private String language;
	
	private String facebook;
	
	private String twitter;
	
	private String linkedin;
	
	private String instagram;
	
	private String printertest;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "favourite_Posts",
	joinColumns = @JoinColumn(name ="user_id"),
	inverseJoinColumns = @JoinColumn(name ="hotel_id"))
	@JsonIgnore
	@Builder.Default
	private Set<Hotel> favouritePosts=new HashSet<Hotel>();
	
	@OneToOne
	@JoinColumn(name = "profile_image_id")
	private UserImage profilePic;
	
	@OneToOne
	@JoinColumn(name = "cover_image_id")
	private UserImage coverPic;
	
	@OneToMany(mappedBy = "agent", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<Hotel> hotels=new HashSet<Hotel>();
	
	@OneToMany(mappedBy = "agent", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<Booking> bookings=new HashSet<Booking>();
	
	@OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<Review> reviews=new HashSet<Review>();
	
	@OneToMany(mappedBy = "agent",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<Messages> messages=new HashSet<Messages>();
}
