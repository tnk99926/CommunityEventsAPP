package com.codestep.portfolio1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.portfolio1app.entities.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String>{
}
