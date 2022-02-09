package com.example.owse.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.example.owse.dto.usersDTO;
import com.example.owse.dto.usersRequestDTO;
import com.example.owse.dto.usersResponseDTO;
import com.example.owse.service.usersService;
import com.example.owse.util.jwtTokenUtil;

import lombok.*;

@RestController
@RequestMapping(value = "/users")
public class usersController {
	@Autowired
	usersService UsersService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private jwtTokenUtil JwtTokenUtil;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public usersResponseDTO login(@RequestBody usersRequestDTO UsersRequestDTO) {
		usersResponseDTO UsersResponseDTO = new usersResponseDTO();
		org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(UsersRequestDTO.getLogin(), UsersRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		 if (authentication.isAuthenticated()) {
	            String token = JwtTokenUtil.generateToken(UsersRequestDTO.getLogin());

	            UsersResponseDTO = usersResponseDTO.builder()
	                    .login(UsersRequestDTO.getLogin())
	                    .token(token)
	                    .build();

	            return UsersResponseDTO;
	        }

	        return UsersResponseDTO;
		
	}
	
	@RequestMapping(value = "/principal", method = RequestMethod.GET)
	public usersDTO principal(Principal principal) {
		usersDTO UsersDTO = UsersService.byLogin(principal.getName());
		
		return UsersDTO;
	}

	// get
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<usersDTO> list(Pageable pageable) {
		return UsersService.List(pageable);
	}

	// id GET
	@RequestMapping(value = "/(id)", method = RequestMethod.GET)
	public usersDTO getById(@PathVariable("id") Long id) {
		return UsersService.byId(id);
	}

	// POST
	@RequestMapping(value = "", method = RequestMethod.POST)
	public usersDTO add(@RequestBody usersDTO UsersDTO) {
		return UsersService.save(UsersDTO);
	}

	// Update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public usersDTO update(@PathVariable("id") Long id, @RequestBody usersDTO UsersDTO) {
        		
		usersDTO.usersDTOBuilder userDTOBuilder = UsersDTO.toBuilder();
		
		
        UsersDTO = userDTOBuilder
                .id(id)
                .build();

        return UsersService.save(UsersDTO);
    }
	
	//delete
	@RequestMapping(value = "/(id)", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id) {
		UsersService.delete(id);
	}

}
