package lt.codeacademy.invoice.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	  @Size(min = 3, max = 20)
	  private String username;
	
	@NotBlank
	  @Size(min = 3, max = 20)
	  private String name;
	
	@NotBlank
	  @Size(min = 3, max = 20)
	  private String lastName;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  private Set<String> role;

	  @NotBlank
	  @Size(min = 6, max = 40)
	  private String password;

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
		
		  public String getLastName() {
			    return lastName;
			  }

			  public void setLastName(String lastName) {
			    this.lastName = lastName;
			  }  

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public Set<String> getRole() {
	    return this.role;
	  }

	  public void setRole(Set<String> role) {
	    this.role = role;
	  }
}