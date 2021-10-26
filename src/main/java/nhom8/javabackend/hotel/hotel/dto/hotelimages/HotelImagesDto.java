package nhom8.javabackend.hotel.hotel.dto.hotelimages;

import nhom8.javabackend.hotel.hotel.entity.Hotel;

public interface HotelImagesDto {
	public Long getId();
	
	public String getUrl();
	
	public String getThumbUrl();
	
	public Hotel getHotel();
}
