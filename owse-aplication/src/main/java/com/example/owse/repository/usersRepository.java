package com.example.owse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.owse.entity.users;

@Repository
public interface usersRepository extends JpaRepository<users, Long> {
	
}
