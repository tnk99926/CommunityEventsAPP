package com.codestep.CommunityEventsApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.CommunityEventsApp.entities.ActivityPrefecture;
import com.codestep.CommunityEventsApp.entities.User;

@Repository
public interface ActivityPrefectureRepository extends JpaRepository<ActivityPrefecture, Long>{
	public List<ActivityPrefecture> findByUser(User user);
	
}