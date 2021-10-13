package nhom8.javabackend.hotel.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.user.dto.message.CreateMessageDto;
import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.dto.message.UpdateMessageDto;
import nhom8.javabackend.hotel.user.entity.Message;
import nhom8.javabackend.hotel.user.service.itf.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	private MessageService service;
	
	public MessageController(MessageService messageService) {
		service=messageService;
	}
	
	@GetMapping("/find-all-message")
	public Object findAllMessage() {
		List<MessageDto> messages=service.findAllMessageDto();
		
		return ResponseHandler.getResponse(messages,HttpStatus.OK);
	}
	
	@PostMapping("/create-new-message")
	public Object createNewMessage(@Valid @RequestBody CreateMessageDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		Message message=service.createNewMessage(dto);
		
		return ResponseHandler.getResponse(message,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-message")
	public Object updateMessage(@Valid @RequestBody UpdateMessageDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		Message message=service.updateMessage(dto);
		
		return ResponseHandler.getResponse(message,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{message-id}")
	public Object deleteMessage(@PathVariable("message-id")Long id) {
		if(!service.isExistedId(id))
			return ResponseHandler.getResponse("Message doesn't exist",HttpStatus.BAD_REQUEST);
		
		service.deleteMessage(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
