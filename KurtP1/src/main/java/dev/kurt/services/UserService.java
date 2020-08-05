package dev.kurt.services;

import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.User;

public interface UserService {
	
	User loginUser(LoginDTO dto);
	
}
