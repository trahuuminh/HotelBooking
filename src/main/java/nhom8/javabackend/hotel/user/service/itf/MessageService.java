package nhom8.javabackend.hotel.user.service.itf;

import java.util.List;

import nhom8.javabackend.hotel.user.dto.message.CreateMessageDto;
import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.dto.message.UpdateMessageDto;
import nhom8.javabackend.hotel.user.entity.Message;

public interface MessageService {
	List<MessageDto> findAllMessageDto();
	
	Message createNewMessage(CreateMessageDto dto);
	
	Message updateMessage(UpdateMessageDto dto);
	
	void deleteMessage(Long id);
}
