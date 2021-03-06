package nhom8.javabackend.hotel.user.service.itf;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.user.dto.message.CreateMessageDto;
import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.dto.message.PagingFormatMessageDto;
import nhom8.javabackend.hotel.user.entity.Message;

public interface MessageService {
	List<MessageDto> findAllMessageDto();
	
	Message createNewMessage(CreateMessageDto dto);
	
	void deleteMessage(Long id);
	
	boolean isExistedId(Long id);
	
	Page<MessageDto> findAllMessageDtoByAgentId(Long agentId, Pageable pageable);
	
	PagingFormatMessageDto pagingFormat(Page<MessageDto> messages);
}
