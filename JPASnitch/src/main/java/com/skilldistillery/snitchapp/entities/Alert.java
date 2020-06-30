package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String subject;

	private String content;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	private Boolean expired;

	@Column(name = "img_url")
	private String imgUrl;

	private Integer zipcode;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// method

	public Alert() {
		super();
	}

	public Alert(int id, Category category, String subject, String content, LocalDateTime createdAt, Boolean expired,
			User user) {
		super();
		this.id = id;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.createdAt = createdAt;
		this.expired = expired;
		this.user = user;
	}

	public Alert(int id, String subject, String content, LocalDateTime createdAt, Boolean expired, String imgUrl,
			int zipcode, User user, Category category) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.createdAt = createdAt;
		this.expired = expired;
		this.imgUrl = imgUrl;
		this.zipcode = zipcode;
		this.user = user;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Alert other = (Alert) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", subject=" + subject + ", content=" + content + ", createdAt=" + createdAt
				+ ", expired=" + expired + ", imgUrl=" + imgUrl + ", zipcode=" + zipcode + ", user=" + user
				+ ", category=" + category + "]";
	}

	
}
