 package nhom8.javabackend.hotel.user.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.dto.message.CreateMessageDto;
import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.dto.message.UpdateMessageDto;
import nhom8.javabackend.hotel.user.entity.Message;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.MessageService;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	private MessageService service;
	private UserService userService;
	private JwtUtils jwt;
	
	public MessageController(MessageService messageService,UserService UserService,JwtUtils Jwt) {
		service=messageService;
		userService=UserService;
		jwt=Jwt;
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
	
	@GetMapping("/find-messages-by-agent-id")
	public Object findAllMessagesByAgentId(@RequestParam("page")Optional<Integer> page, @RequestParam("agentId") Long agentId, HttpServletRequest request) {
		try {
			if(!userService.isExistedId(agentId))
				return ResponseHandler.getResponse("Agent doesn't exist",HttpStatus.BAD_REQUEST);
			
			else if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			
			User currentUser=userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(!currentUser.getRole().equals(Role.ADMIN) && currentUser.getId() != agentId)
				return ResponseHandler.getResponse("you're not allowed to find all Messages of this agent !", HttpStatus.BAD_REQUEST);
			
			Pageable pageable = PageRequest.of(page.orElse(0),22, Sort.by("id"));
			Page<MessageDto> messages=service.findAllMessageDtoByAgentId(agentId, pageable);
			return ResponseHandler.getResponse(service.pagingFormat(messages),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
}
