package nhom8.javabackend.hotel.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelCoverPicDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.UpdateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.repository.HotelImagesRepository;
import nhom8.javabackend.hotel.hotel.repository.HotelRepository;
import nhom8.javabackend.hotel.hotel.service.itf.HotelImagesService;

@Service
public class HotelImagesServiceImpl implements HotelImagesService {

	private HotelImagesRepository hotelImagesRepo;
	private HotelRepository hotelRepo;
	
	public HotelImagesServiceImpl(HotelImagesRepository hotelImagesRepository, HotelRepository hotelRepository) {
		hotelImagesRepo=hotelImagesRepository;
		hotelRepo=hotelRepository;
	}
	
	@Override
	public List<HotelImagesDto> findAllHotelImagesDto() {
		return hotelImagesRepo.findAllHotelImagesDto();
	}


	@Override
	public HotelImages createNewHotelCoverPic(CreateHotelCoverPicDto dto) {
		HotelImages hotelImages=new HotelImages();
		
		hotelImages.setUrl(dto.getUrl());
		hotelImages.setThumbUrl(dto.getThumbUrl());
		
		return hotelImagesRepo.save(hotelImages);
	}

	@Override
	public HotelImages updateHotelImages(UpdateHotelImagesDto dto) {
		HotelImages hotelImages=hotelImagesRepo.getById(dto.getHotelImagesId());
		
		if(dto.getHotelId() != null && dto.getHotelId() != 0) {
			Hotel hotel=hotelRepo.getById(dto.getHotelId());
			hotelImages.setHotel(hotel);
		}
		
		hotelImages.setUrl(dto.getUrl());
		hotelImages.setThumbUrl(dto.getThumbUrl());
		
		return hotelImagesRepo.save(hotelImages);
	}

	@Override
	public void deleteHotelCoverPic(Long id) {
		hotelImagesRepo.deleteById(id);
	}

	@Override
	public HotelImages createNewHotelImages(CreateHotelImagesDto dto) {
		HotelImages hotelImages=new HotelImages();
		
		if(dto.getHotelId() != null && dto.getHotelId() != 0) {
			Hotel hotel=hotelRepo.getById(dto.getHotelId());
			hotelImages.setHotel(hotel);
		}
		
		hotelImages.setUrl(dto.getUrl());
		hotelImages.setThumbUrl(dto.getThumbUrl());
		
		return hotelImagesRepo.save(hotelImages);
	}
	

	@Override
	public HotelImages createMultipleNewHotelImages(String url, String thumbUrl, Long hotelId) {
		HotelImages hotelImages=new HotelImages();
		
		if(hotelId != null && hotelId != 0) {
			Hotel hotel=hotelRepo.getById(hotelId);
			hotelImages.setHotel(hotel);
		}
		
		hotelImages.setUrl(url);
		hotelImages.setThumbUrl(thumbUrl);
		
		return hotelImagesRepo.save(hotelImages);
	}

}
