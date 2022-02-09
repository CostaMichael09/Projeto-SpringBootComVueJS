package com.example.owse.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Entity(name = "users")
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String login;
	@Column
	private String password;
	
    
	
}
