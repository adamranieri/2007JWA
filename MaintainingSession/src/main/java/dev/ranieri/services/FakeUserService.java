package dev.ranieri.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ranieri.dtos.LoginDTO;
import dev.ranieri.entities.User;

public class FakeUserService implements UserService {
	
	private Map<Integer,User> users = new HashMap<Integer,User>();
	
	// instance blocks these blocks will execute when you create an object
	{
		User adam = new User(101,"rayman","java4eva","Adam Ranieri");
		User david = new User(202,"davewolfe","csharp","David Aguilar");
		User kyle = new User(303,"dutch","jsblackmagic","Kyle van Sprewenburg");
		this.users.put(adam.getId(), adam);
		this.users.put(david.getId(), david);
		this.users.put(kyle.getId(), kyle);
	}

	@Override
	public User loginUser(LoginDTO dto) {
		
		List<User> userList = new ArrayList<User>(users.values());		
		
		for(User s : userList) {
			
			if(s.getUsername().equals(dto.getUsername()) && s.getPassword().equals(dto.getPassword())) {
				return s;
			}
			
		}
		
		return null;
	}

}
