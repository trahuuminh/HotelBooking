package nhom8.javabackend.hotel.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhom8.javabackend.hotel.user.dto.message.MessageDto;
import nhom8.javabackend.hotel.user.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query("SELECT m FROM Message m")
	List<MessageDto> findAllMessageDto();
}
