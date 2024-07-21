package com.codestep.CommunityEventsApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.CommunityEventsApp.entities.Prefecture;
import com.codestep.CommunityEventsApp.repositories.PrefectureRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrefectureService {
	
	@Autowired
	PrefectureRepository prefectureRepository;
	
	public List<Prefecture> findAll(){
		return prefectureRepository.findAll();
	}
	
	public Prefecture getById(long id) {
		return prefectureRepository.getReferenceById(id);
	}
	
}
