package com.codestep.CommunityEventsApp.entities;

import java.util.List;

import com.codestep.CommunityEventsApp.Annotations.Password;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = {"activityPrefectures"}) 
public class User {
    
    @Id
    @Size(max = 10, message="ユーザー名は10文字以下で入力してください")
	@NotBlank(message = "ユーザー名は必須です")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 8,max = 8, message="パスワードは8文字で入力してください")
	@NotBlank(message = "パスワードは必須です")
    @Password
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean enabled;
    
    @OneToMany(mappedBy = "user")
	private List<ActivityPrefecture> activityPrefectures;
    
}
