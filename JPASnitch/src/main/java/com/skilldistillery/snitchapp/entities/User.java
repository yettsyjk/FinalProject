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
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@OneToMany(mappedBy="user")
	private List <Alert> alerts;
	// relationship between User observer and Snitches(user creates snitches)
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <Snitch> snitchesCreated;
	// relationship between Users and Snitches(users voting on snitches)
	// join on table snitch_vote
	@JsonIgnore
	@OneToMany (mappedBy = "user")
	private List <SnitchVote> votes;
	@JsonIgnore
	@OneToMany (mappedBy ="user")
	private List <Comment> comments;
	//methods
	public User() {
		super();
	}
	public User(int id, String username, String email, String password, String firstName, String lastName,
			Boolean enabled, String pictureUrl, LocalDateTime createDate, Role role, List<Alert> alerts,
			List<Snitch> snitchesCreated, List<SnitchVote> votes, List<Comment> comments) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.pictureUrl = pictureUrl;
		this.createDate = createDate;
		this.role = role;
		this.alerts = alerts;
		this.snitchesCreated = snitchesCreated;
		this.votes = votes;
		this.comments = comments;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public List<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
	public List<Snitch> getSnitchesCreated() {
		return snitchesCreated;
	}
	public void setSnitchesCreated(List<Snitch> snitchesCreated) {
		this.snitchesCreated = snitchesCreated;
	}
	public List<SnitchVote> getVotes() {
		return votes;
	}
	public void setVotes(List<SnitchVote> votes) {
		this.votes = votes;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", pictureUrl="
				+ pictureUrl + ", createDate=" + createDate + ", role=" + role + "]";
	}
}