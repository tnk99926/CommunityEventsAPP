package com.codestep.portfolio1app.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "prefectures")
@Data
public class Prefecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "username")
	private List<User> users;
	
	@OneToMany(mappedBy = "id")
	private List<ActivityPrefecture> activityPrefectures;
}
