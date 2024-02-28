package com.net.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.net.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

	Users findByUsername(String username);
	
	List<Users> findAll();
}
