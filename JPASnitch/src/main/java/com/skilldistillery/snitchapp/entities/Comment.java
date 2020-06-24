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
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@CreationTimestamp
	@Column (name= "create_date")
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn (name= "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn (name = "snitch_id")
	private Snitch snitch;
	
	//methods
	
	public Comment() {
		super();
	}

	public Comment(int id, Snitch snitch, User user, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.snitch = snitch;
		this.user = user;
		this.content = content;
		this.createDate = createDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Snitch getSnitch() {
		return snitch;
	}


	public void setSnitch(Snitch snitch) {
		this.snitch = snitch;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}


	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", snitch=" + snitch + ", user=" + user + ", content=" + content + ", createDate="
				+ createDate + "]";
	}


	
}
