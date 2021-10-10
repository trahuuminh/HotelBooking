package nhom8.javabackend.hotel.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {
	@Bean
	AuditorAware<String>auditorProvider(){
		return new AuditorAwareImpl();
	}
	
	public class AuditorAwareImpl implements AuditorAware<String>{

		@Override
		public Optional<String> getCurrentAuditor() {
			String currentUsername=SecurityContextHolder.getContext()
														.getAuthentication()
														.getName();
			return Optional.ofNullable(currentUsername);
		}
		
		
	}
}
