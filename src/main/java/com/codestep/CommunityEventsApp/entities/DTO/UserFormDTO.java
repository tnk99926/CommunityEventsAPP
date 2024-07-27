package com.codestep.CommunityEventsApp.entities.DTO;

import java.util.List;

import com.codestep.CommunityEventsApp.entities.User;
import com.codestep.CommunityEventsApp.entities.UserInformation;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class UserFormDTO {
	@Valid
	public User user = new User();
	@Valid
	public UserInformation userInformation = new UserInformation();

	public List<Long> activityPrefectureIds;
}
