package com.codestep.portfolio1app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "activity_prefectures")
@Data
public class ActivityPrefecture {
	
	public ActivityPrefecture(long prefectureId) {
		this.prefectureId = prefectureId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="username", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="prefecture_id",insertable = false, updatable = false, nullable = false)
	private Prefecture prefecture ;//マッピング用フィールド。db更新なし
	
	@Column(name = "prefecture_id", nullable = false)
    @Min(value= 1, message="県名の指定に誤りがあります")
    @Max(value= 47, message="県名の指定に誤りがあります")
    private Long prefectureId;
}
