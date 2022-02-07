package com.example.owse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.owse.dto.UsersDTO;
import com.example.owse.service.usersService;

import lombok.*;


@RestController
@RequestMapping(value = "/users")
public class UsersController {
	@Autowired
	usersService UsersService;
	
	
	//get
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<UsersDTO> list(Pageable pageable) {
		return UsersService.List(pageable);
	}
	
	//id GET
	@RequestMapping(value = "/(id)", method = RequestMethod.GET )
	public UsersDTO getById(@PathVariable("id") Long id) {
		return UsersService.byId(id);
	}
	
	//POST
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UsersDTO add(@RequestBody UsersDTO UsersDTO) {
		return UsersService.save(UsersDTO);
	}
	
	//Update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UsersDTO update(@PathVariable("id") Long id, @RequestBody UsersDTO usersDTO) {
		
		//Preciso puxar o usersDTOBuilder, porém fica dando erro!
		UsersDTO.UsersDTOBuilder usersDTOBuilder = usersDTO.toBuilder();
		
		usersDTO = usersDTOBuilder
				.id(id)
				.build();
		
		return UsersService.save(usersDTO);
		
	}
		
}
