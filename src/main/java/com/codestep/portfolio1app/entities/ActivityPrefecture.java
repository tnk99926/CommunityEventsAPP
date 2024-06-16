package com.codestep.portfolio1app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "activity_prefectures")
@Data
public class ActivityPrefecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="prefecture_id")
	private Prefecture prefecture ;
}
