package nhom8.javabackend.hotel.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nhom8.javabackend.hotel.security.dto.UserDetailsDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository repository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		repository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repository.getByUsername(username);
		
		
		if(user==null)
			throw new UsernameNotFoundException("Username is not existed.");
		
		Set<GrantedAuthority>authorities=new HashSet<GrantedAuthority>();
		String roleName=user.getRole().name();
		authorities.add(new SimpleGrantedAuthority(roleName));
		
		return new UserDetailsDto(username, user.getPassword(), authorities); 
	}

	
	
}
