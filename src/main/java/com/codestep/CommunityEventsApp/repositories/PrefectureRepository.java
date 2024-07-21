package com.codestep.CommunityEventsApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.CommunityEventsApp.entities.Prefecture;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Long>{
}
