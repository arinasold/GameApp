package backendLastProject.GamesApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//username (unique), password, email, role

@Entity(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name= "username", nullable = false, updatable = false)
	private String username;
	
	@Column(name= "password", nullable = false)
	private String passwordHash;
	
	@Column(name= "role", nullable = false)
	private String role;
	
	
	
	public User() {
		super();
	}
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public String getRole() {
		return role;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public void setRole(String role) {
		this.role = role;
	}

	

}
