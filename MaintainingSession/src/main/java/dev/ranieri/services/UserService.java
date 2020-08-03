package dev.ranieri.services;

import dev.ranieri.dtos.LoginDTO;
import dev.ranieri.entities.User;

public interface UserService {
	
	// Crud operations
	
	
	// login method in my service
	// it will use the information in the DTO to get a user
	public User loginUser(LoginDTO dto);
	

}
