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


// questions on relationship between User & Comment
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn (name = "snitch_id")
	private Snitch snitch;
	
	@Column (name= "user_id")
	private User user;
	
	private String content;
	
	@CreationTimestamp
	@Column (name= "create_date")
	private LocalDateTime createDate;

	
	//methods
	public Comment() {
		super();
	}


	
}
