package com.example.owse.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.owse.entity.users;
import com.example.owse.repository.usersRepository;

@Service
public class jwtUserService implements UserDetailsService{

	@Autowired
	usersRepository UsersRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		users Users = UsersRepository.findByLogin(username);
		
		if(Users == null) {
			throw new UsernameNotFoundException("Usuario nào existe");
		}
		
		
		return new org.springframework.security.core.userdetails.User(Users.getLogin(), Users.getPassword(), getAuthority());

	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	

}
