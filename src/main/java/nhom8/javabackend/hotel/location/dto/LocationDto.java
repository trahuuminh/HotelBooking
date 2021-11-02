package nhom8.javabackend.hotel.location.dto;

public interface LocationDto {
	public Long getId();
	
	public float getLat();
	
	public float getLng();
	
	public String getFormattedAddress();
	
	public String getZipcode();
	
	public String getCity();
	
	public String getStateLong();
	
	public String getStateShort();
	
	public String getCountryLong();
	
	public String getCountryShort();
	
	public int getNumberOfPost();
	
}
