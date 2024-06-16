package com.codestep.portfolio1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codestep.portfolio1app.entities.Prefecture;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Long>{
}
