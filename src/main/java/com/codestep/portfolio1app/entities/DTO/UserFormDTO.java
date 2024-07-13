package com.codestep.portfolio1app.entities.DTO;

import java.util.List;

import com.codestep.portfolio1app.entities.User;
import com.codestep.portfolio1app.entities.UserInformation;

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
