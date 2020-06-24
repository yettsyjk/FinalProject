package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	//@Email(message = "Please enter a valid email address")
	private String email;
	
	private String password;
	
	//@Size (min=2, max=50)
	@Column (name= "first_name")
	private String firstName;
	
	@Column (name= "last_name")
	private String lastName;
	
	private Boolean enabled;
	
	@Column (name= "picture_url")
	private String pictureUrl;
	
	@CreationTimestamp
	@Column (name= "create_date")
	private LocalDateTime createDate;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy="userId")
	private List <Alert> alerts;
	
	// relationship between User observer and Snitches(user creates snitches)
	@OneToMany(mappedBy="userId")
	private List <Snitch> snitchesCreated;
	
	// relationship between Users and Snitches(users voting on snitches)
	// join on table snitch_vote
	@ManyToMany(mappedBy="observers")
	private List <Snitch> snitchVotes;
	
	
	//methods
	
	public User() {
		super();
	}

	public User(int id, String username, String email, String password, String firstname, String lastName,
			Boolean enabled, String pictureUrl, LocalDateTime createDate, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastName;
		this.enabled = enabled;
		this.pictureUrl = pictureUrl;
		this.createDate = createDate;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", firstname=" + firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", pictureUrl="
				+ pictureUrl + ", createDate=" + createDate + "]";
	}
	
	
}
