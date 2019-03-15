package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	@Query("SELECT u FROM #{#entityName} u WHERE u.status='ACTIVE'")
    public List<User> findActiveUsers();
}
