package nhom8.javabackend.hotel.user.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.common.entity.BaseEntity;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.user.util.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"favouritePost", "listedPost"})
@EqualsAndHashCode(exclude = {"favouritePost", "listedPost"},callSuper = false)
@Entity
@Table(name = "users")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","listedPost"})
public class User extends BaseEntity {
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private String username;
	
	@NotNull
	@JsonIgnore
	private String password;
	
	@NotNull
	@Email
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
	
	private String pinterest;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_favourite_post",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "hotel_id"))
	@JsonIgnore
	@Builder.Default
	private Set<Hotel> favouritePost = new HashSet<Hotel>();
	
	@OneToOne
	@JoinColumn(name = "profile_pic_id")
	private UserImage profilePic;
	
	@OneToOne
	@JoinColumn(name = "cover_pic_id")
	private UserImage coverPic;
	
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonIgnore
	@Builder.Default
	private Set<Hotel> listedPost = new HashSet<Hotel>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonIgnore
	@Builder.Default
	private Set<Booking> bookings = new HashSet<Booking>();
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonIgnore
	@Builder.Default
	private Set<Review> reviews = new HashSet<Review>();
	
	@OneToMany(mappedBy = "agent",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonIgnore
	@Builder.Default
	private Set<Message> messages = new HashSet<Message>();
	
	public void addHotel(Hotel hotel) {
		this.favouritePost.add(hotel);
		hotel.getUsersFavourite().add(this);
		
	}
	
	public void removeHotel(Hotel hotel) {
		this.favouritePost.remove(hotel);
		hotel.getUsersFavourite().remove(this);
	}
}
