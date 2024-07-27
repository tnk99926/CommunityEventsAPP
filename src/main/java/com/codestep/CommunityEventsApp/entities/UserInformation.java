package com.codestep.CommunityEventsApp.entities;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user_informations")
@Data
public class UserInformation {
	@Id
    private String username;
	
	@MapsId
	@OneToOne
    @JoinColumn(name = "username",nullable = false)
    private User user;
	
	@Column(length = 50, nullable = false)
	@Email
	@NotBlank(message = "メールアドレスは必須です")
	private String email;
	
	
	@ManyToOne
	@JoinColumn(name = "prefecture_id",insertable = false, updatable = false, nullable = false)
    private Prefecture prefecture;//マッピング用フィールド。db更新なし
	
	@Column(name = "prefecture_id", nullable = false)
	@NotNull(message="居住地は必須です")
    @Min(value=1,message="居住地は必須です")
    @Max(value=47,message="県名の指定に誤りがあります")
    private Long prefectureId;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime created;
	
	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime updated;
	
	
}
