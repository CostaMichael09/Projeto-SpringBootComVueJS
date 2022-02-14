package com.example.owse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.owse.dto.usersDTO;
import com.example.owse.entity.User;
import com.example.owse.repository.usersRepository;
import com.example.owse.service.mapper.usersMapper;

@Service
public class usersService {
	
	@Autowired
	usersRepository UsersRepository;
	@Autowired
	usersMapper UsersMapper;
	
	
	public Page<usersDTO> List(Pageable pageable){
		Page<User> UsersPage = UsersRepository.findAll(pageable);
		int totalElements = UsersPage.getNumberOfElements();
		
		return UsersMapper.usersPageToUserDTOPage(UsersPage, pageable, totalElements);
	}
	
	public usersDTO save(usersDTO UsersDTO) {
		User Users = UsersMapper.usersDTOToUser(UsersDTO);
		Users = UsersRepository.save(Users);
		
		return UsersMapper.usersToUserDTO(Users);
	}
	
	public usersDTO byId(Long id) {
		User Users = UsersRepository.getOne(id);
		return UsersMapper.usersToUserDTO(Users);
	}
	
	public void delete(Long id) {
		UsersRepository.deleteById(id);
	}

	public usersDTO byLogin(String name) {
		
		User Users = UsersRepository.findByLogin(name);
		
		return UsersMapper.usersToUserDTO(Users);
	}
}
