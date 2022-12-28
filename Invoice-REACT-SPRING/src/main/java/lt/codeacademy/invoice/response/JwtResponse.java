package lt.codeacademy.invoice.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String name;
	private String lastName;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String name, String lastName, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	
	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}	

	public List<String> getRoles() {
		return roles;
	}

}
