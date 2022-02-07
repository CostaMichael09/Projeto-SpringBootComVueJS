package com.example.owse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.owse.dto.usersDTO;
import com.example.owse.service.usersService;

import lombok.*;


@RestController
@RequestMapping(value = "/users")
public class usersController {
	@Autowired
	usersService UsersService;
	
	
	//get
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<usersDTO> list(Pageable pageable) {
		return UsersService.List(pageable);
	}
	
	//id GET
	@RequestMapping(value = "/(id)", method = RequestMethod.GET )
	public usersDTO getById(@PathVariable("id") Long id) {
		return UsersService.byId(id);
	}
	
	//POST
	@RequestMapping(value = "", method = RequestMethod.POST)
	public usersDTO add(@RequestBody usersDTO UsersDTO) {
		return UsersService.save(UsersDTO);
	}
	
	//Update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public usersDTO update(@PathVariable("id") Long id, @RequestBody usersDTO UsersDTO) {
		
		//Preciso puxar o usersDTOBuilder, porém fica dando erro!
		usersDTO.usersDTOBuilder usersDTOBuilder = UsersDTO.toBuilder();
		
		UsersDTO = usersDTOBuilder
				.id(id)
				.build();
		
		return UsersService.save(UsersDTO);
		
	}
		
}
