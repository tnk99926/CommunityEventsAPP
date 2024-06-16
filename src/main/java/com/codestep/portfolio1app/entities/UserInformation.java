package com.codestep.portfolio1app.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "user_informations")
@Data
public class UserInformation {
	@Id
    private String username;
	
	@MapsId
	@NotNull
	@OneToOne
    @JoinColumn(name = "username",nullable = false)
    private User user;
	
	@Column(length = 50, nullable = false)
	@Size(max = 50, message="アドレスは50文字以下で入力してください")
	@Email
	@NotBlank(message = "メールアドレスは必須です")
	private String email;
	
	@NotNull(message="居住地は必須です")
	@ManyToOne
	@JoinColumn(name = "prefecture_id")
    private Prefecture prefecture;
	
	@Column(nullable = true)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime created;
	
	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime updated;
	
	
}
