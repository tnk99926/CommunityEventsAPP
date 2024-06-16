package com.codestep.portfolio1app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.portfolio1app.entities.ActivityPrefecture;
import com.codestep.portfolio1app.entities.User;

@Repository
public interface ActivityPrefectureRepository extends JpaRepository<ActivityPrefecture, Long>{
	public List<ActivityPrefecture> getByUser(User user);
}
