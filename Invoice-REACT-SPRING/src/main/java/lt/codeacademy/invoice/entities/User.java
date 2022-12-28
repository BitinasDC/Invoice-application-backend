package lt.codeacademy.invoice.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table( name = "users",
uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 20 )
	private String username;
	
	@NotBlank
	@Size(max = 20 )
	private String name;
	
	@NotBlank
	@Size(max = 20 )
	private String lastName;
	
	@NotBlank
	@Size(max = 50 )
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 120 )
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable (name = "user_roles", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Role roles;

	public User(@NotBlank @Size(max = 20) String username, 
			@NotBlank @Size(max = 50) 
			@Email String email,
			@NotBlank @Size(max = 120) String password) {
		this.username = username;
		this.email = email;
		this.password = password;	
	}
	
	public User(@NotBlank @Size(max = 20) String username, 
			@NotBlank @Size(max = 20) String name, 
			@NotBlank @Size(max = 20) String lastName, 
			@NotBlank @Size(max = 50) 
			@Email String email,
			@NotBlank @Size(max = 120) String password) {
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;	
	}
	
	 public Long getId() {
		    return id;
		  }

		  public void setId(Long id) {
		    this.id = id;
		  }

		  public String getUsername() {
		    return username;
		  }

		  public void setUsername(String username) {
		    this.username = username;
		  }

		  public String getName() {
			    return name;
			}

		  public void setName(String name) {
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

		  public Role getRoles() {
		    return roles;
		  }

		  public void setRoles(Role roles) {
		    this.roles = roles;
		  }
		}
	
	

	

	


