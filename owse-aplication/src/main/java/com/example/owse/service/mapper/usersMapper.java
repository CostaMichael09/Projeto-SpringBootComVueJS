package com.example.owse.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.owse.dto.usersDTO;
import com.example.owse.entity.users;

@Service
public class usersMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public users usersDTOToUser(usersDTO UserDTO) {
		return modelMapper.map(UserDTO,  users.class);
		
	}
	
	public usersDTO usersToUserDTO(users User) {
		return modelMapper.map(User, usersDTO.class);
	}
	
	public List<users> usersDTOListToUserList(List<usersDTO> usersDTOList){
		return usersDTOList.stream()
				.map(this::usersDTOToUser)
				.collect(Collectors.toList());
	}
	
	public List<usersDTO> usersListToUserDTOList(List<users> usersList){
		return usersList.stream()
				.map(this::usersToUserDTO)
				.collect(Collectors.toList());
	}
	
	public Page<usersDTO> usersPageToUserDTOPage(Page<users> usersPage, Pageable pageable, int total){
		return new PageImpl<usersDTO>(
			usersPage.stream()
				.map(this::usersToUserDTO)
				.collect(Collectors.toList()), pageable, total);
		
	}
}
