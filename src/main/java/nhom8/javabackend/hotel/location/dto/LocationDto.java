package nhom8.javabackend.hotel.location.dto;

public interface LocationDto {
	public Long getId();
	
	public int getLat();
	
	public int getLng();
	
	public String getFormattedAddress();
	
	public String getZipcode();
	
	public String getCity();
	
	public String getStateLong();
	
	public String getStateShort();
	
	public String getCountryLong();
	
	public String getCountryShort();
	
	public int getNumberOfPost();
	
}
