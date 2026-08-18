package com.utp.ms_auth.service;

import com.utp.ms_auth.entity.User;
import com.utp.ms_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())  // <- usa email como "username" interno
				.password(user.getPassword())
				.authorities(user.getRoles().stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()))
				.disabled(!user.isEnabled())
				.build();
	}
    
}