package nhom8.javabackend.hotel.hotel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
import nhom8.javabackend.hotel.booking.entity.Review;
import nhom8.javabackend.hotel.common.entity.BaseEntity;
import nhom8.javabackend.hotel.hotel.util.Status;
import nhom8.javabackend.hotel.user.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString/*(exclude = {"favouriteUsers"})*/
@EqualsAndHashCode(/*exclude = {"favouriteUsers"},*/callSuper = false)
@Entity
@Table(name = "hotel")
public class Hotel extends BaseEntity {
	
	private String title;
	
	private String slug;
	
	private String content;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@NotNull
	private String price;
	
	private boolean isNegotiable;
	
	private String propertyType;
	
	private String condition;
	
	@NotNull
	private int rating;
	
	@NotNull
	private int ratingCount;
	
	@NotNull
	private String contactNumber;
	
	private String termsAndCondition;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private User agent; 
	
	@OneToOne
	@JoinColumn(name = "amenity_id")
	private Amenities amenity;
	
	@OneToMany(mappedBy = "hotel",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<HotelImages> images=new HashSet<HotelImages>();
	
	@OneToOne
	@JoinColumn(name = "cover_image_id")
	private HotelImages coverImage;
	
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "favouritePosts")
	@Builder.Default
	private Set<User> favouriteUsers=new HashSet<User>();
	
	@OneToMany(mappedBy = "hotel", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonIgnore
	@Builder.Default
	private Set<Review> reviews=new HashSet<Review>();
}
