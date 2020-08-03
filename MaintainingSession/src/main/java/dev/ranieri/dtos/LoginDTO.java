package dev.ranieri.dtos;

// Data transfer object
// unlike an entity it is not saved in the database
// it is an object used to send information to or from the client and api
public class LoginDTO {
	
	private String username;
	private String password;
		
	public LoginDTO() {
		super();
	}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
