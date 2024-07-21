package com.codestep.CommunityEventsApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.CommunityEventsApp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	public User getByUsername(String username);
}
