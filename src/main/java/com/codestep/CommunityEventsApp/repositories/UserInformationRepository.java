package com.codestep.CommunityEventsApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.CommunityEventsApp.entities.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String>{
	public UserInformation getByUsername(String username);
}
