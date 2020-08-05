package dev.kurt.services;

import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.User;
import dev.kurt.exceptions.InvalidLoginException;

public interface UserService {
	
	User loginUser(LoginDTO dto) throws InvalidLoginException;
	
}
