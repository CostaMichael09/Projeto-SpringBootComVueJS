package com.example.owse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.owse.dto.UsersDTO;
import com.example.owse.entity.users;
import com.example.owse.repository.usersRepository;
import com.example.owse.service.mapper.usersMapper;

@Service
public class usersService {
	
	@Autowired
	usersRepository UsersRepository;
	@Autowired
	usersMapper UsersMapper;
	
	
	public Page<UsersDTO> List(Pageable pageable){
		Page<users> UsersPage = UsersRepository.findAll(pageable);
		int totalElements = UsersPage.getNumberOfElements();
		
		return UsersMapper.usersPageToUserDTOPage(UsersPage, pageable, totalElements);
	}
	
	public UsersDTO save(UsersDTO UsersDTO) {
		users Users = UsersMapper.usersDTOToUser(UsersDTO);
		Users = UsersRepository.save(Users);
		
		return UsersMapper.usersToUserDTO(Users);
	}
	
	public UsersDTO byId(Long id) {
		users Users = UsersRepository.getOne(id);
		return UsersMapper.usersToUserDTO(Users);
	}
	
	public void delete(Long id) {
		UsersRepository.deleteById(id);
	}
}
