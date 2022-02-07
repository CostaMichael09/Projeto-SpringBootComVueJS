package com.example.owse.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UsersDTO {
	
	private Long id;
	private String name;
	private String login;
	private String password;
}
