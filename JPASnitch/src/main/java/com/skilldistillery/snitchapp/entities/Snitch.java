package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Snitch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn (name= "address_id")
	private Address address;
	
	private String title;
	
	private String description;	

	@CreationTimestamp
	@Column (name= "create_date")
	private LocalDateTime createDate;
	
	@Column (name= "img_url")
	private String imgUrl; 
	
	private Boolean enabled;
	
	private Boolean resolved;
	
	@Column (name= "resolution_date")
	private LocalDateTime resolutionDate;
	
	private String resolution;

	// relationship between Snitches and User observer(user creates snitches)
	@ManyToOne
	@JoinColumn (name= "user_id")
	private User user;
	
	// relationship between Snitches and Users(users voting on snitches)
	// join on table snitch_vote
	@ManyToMany
	@JoinTable (name= "snitch_vote", // JSON IGNORE???? CASCADE ????
		joinColumns = @JoinColumn(name= "snitch_id"),
		inverseJoinColumns= @JoinColumn(name = "user_id"))
	private List<User> observers;
	
	@ManyToOne
	@JoinColumn (name= "category_id")
	private Category category;
	
	@OneToMany (mappedBy = "snitch")
	private List<Comment> comments;
	
	//methods
	
	public Snitch() {
		super();
	}

	public Snitch(int id, Address address, String title, String description, LocalDateTime createDate, String imgUrl,
			Boolean enabled, Boolean resolved, LocalDateTime resolutionDate, String resolution, User user,
			List<User> observers, Category category, List<Comment> comments) {
		super();
		this.id = id;
		this.address = address;
		this.title = title;
		this.description = description;
		this.createDate = createDate;
		this.imgUrl = imgUrl;
		this.enabled = enabled;
		this.resolved = resolved;
		this.resolutionDate = resolutionDate;
		this.resolution = resolution;
		this.user = user;
		this.observers = observers;
		this.category = category;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	public LocalDateTime getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(LocalDateTime resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getObservers() {
		return observers;
	}

	public void setObservers(List<User> observers) {
		this.observers = observers;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Snitch other = (Snitch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Snitch [id=" + id + ", address=" + address + ", title=" + title + ", description=" + description
				+ ", createDate=" + createDate + ", imgUrl=" + imgUrl + ", enabled=" + enabled + ", resolved="
				+ resolved + ", resolutionDate=" + resolutionDate + ", resolution=" + resolution + ", user=" + user
				+ ", observers=" + observers + ", category=" + category + ", comments=" + comments + "]";
	}



}
