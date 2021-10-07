package nhom8.javabackend.hotel.hotel.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.UpdateHotelImagesDto;
//import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.repository.HotelImagesRepository;
import nhom8.javabackend.hotel.hotel.service.itf.HotelImagesService;

@Service
public class HotelImagesServiceImpl implements HotelImagesService {

	private HotelImagesRepository hotelImagesRepo;
	
	public HotelImagesServiceImpl(HotelImagesRepository hotelImagesRepository) {
		hotelImagesRepo=hotelImagesRepository;
	}
	
	@Override
	public List<HotelImagesDto> findAllHotelImagesDto() {
		return hotelImagesRepo.findAllHotelImagesDto();
	}

	@Transactional
	@Override
	public HotelImages createNewHotelImages(CreateHotelImagesDto dto) {
		HotelImages hotelImages=new HotelImages();
		hotelImages.setUrl(dto.getUrl());
		hotelImages.setThumbUrl(dto.getThumbUrl());
		
		return hotelImagesRepo.save(hotelImages);
	}

	@Override
	public HotelImages updateHotelImages(UpdateHotelImagesDto dto) {
		HotelImages hotelImages=hotelImagesRepo.getById(dto.getHotelImagesId());
		
		hotelImages.setUrl(dto.getUrl());
		hotelImages.setThumbUrl(dto.getThumbUrl());
		
		return hotelImagesRepo.save(hotelImages);
	}

	@Override
	public void deleteHotelImages(Long id) {
		hotelImagesRepo.deleteById(id);
	}

}