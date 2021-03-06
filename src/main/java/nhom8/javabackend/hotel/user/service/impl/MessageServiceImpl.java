package nhom8.javabackend.hotel.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.user.dto.message.CreateMessageDto;
import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.dto.message.PagingFormatMessageDto;
import nhom8.javabackend.hotel.user.entity.Message;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.repository.MessageRepository;
import nhom8.javabackend.hotel.user.repository.UserRepository;
import nhom8.javabackend.hotel.user.service.itf.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	private MessageRepository messageRepo;
	private UserRepository userRepo;
	
	public MessageServiceImpl(MessageRepository messageRepository,UserRepository userRepository) {
		messageRepo=messageRepository;
		userRepo=userRepository;
	}
	
	@Override
	public List<MessageDto> findAllMessageDto() {
		return messageRepo.findAllMessageDto();
	}

	@Override
	@Transactional
	public Message createNewMessage(CreateMessageDto dto) {
		Message message=new Message();
		User agent=userRepo.getById(dto.getAgentId());
		
		message.setAgent(agent);
		message.setSenderEmail(dto.getSenderEmail());
		message.setSenderContact(dto.getSenderContact());
		message.setMessage(dto.getMessage());
		
		return messageRepo.save(message);
	}

	@Override
	public void deleteMessage(Long id) {
		messageRepo.deleteById(id);
	}

	@Override
	public boolean isExistedId(Long id) {
		return messageRepo.existsById(id);
	}

	@Override
	public Page<MessageDto> findAllMessageDtoByAgentId(Long agentId, Pageable pageable) {
		return messageRepo.findAllMessageDtoByAgentId(agentId, pageable);
	}

	@Override
	public PagingFormatMessageDto pagingFormat(Page<MessageDto> page) {
		PagingFormatMessageDto dto =new PagingFormatMessageDto();
		
		dto.setPageSize(page.getSize());
		dto.setTotalRecordCount(page.getTotalElements());
		dto.setPageNumber(page.getNumber());
		dto.setRecords(page.toList());
		
		return dto;
	}

}
