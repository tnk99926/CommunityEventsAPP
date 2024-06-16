package com.codestep.portfolio1app.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String password;
    
    @Column
    private boolean enabled;
    
    @OneToMany(mappedBy = "user")
	private List<ActivityPrefecture> activityPrefectures;
    
}
