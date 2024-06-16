package com.codestep.portfolio1app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.portfolio1app.entities.Prefecture;
import com.codestep.portfolio1app.repositories.PrefectureRepository;

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
