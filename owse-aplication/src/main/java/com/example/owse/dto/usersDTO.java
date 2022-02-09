package com.example.owse.dto;

//import lombok.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;



@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class usersDTO {
	
	
	private Long id;
	private String name;
	private String login;
	private String password;
	
	
}
