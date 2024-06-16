package com.codestep.portfolio1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.portfolio1app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	public User getByUsername(String username);
}
