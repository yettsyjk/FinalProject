package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Table (name="snitch_vote")
@Entity
public class SnitchVote {    

	@Column (name="user_id")
	private Integer userId;
	
	@Column (name="snitch_id")
	private Integer snitchId;
	
	private Boolean vote;
	
	@CreationTimestamp
	@Column (name= "create_time")
	private LocalDateTime createTime;
	
	private String note;

	
	//methods
	public SnitchVote() {
		super();
	}


	public SnitchVote(Integer userId, Integer snitchId, Boolean vote, LocalDateTime createTime, String note) {
		super();
		this.userId = userId;
		this.snitchId = snitchId;
		this.vote = vote;
		this.createTime = createTime;
		this.note = note;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getSnitchId() {
		return snitchId;
	}


	public void setSnitchId(Integer snitchId) {
		this.snitchId = snitchId;
	}


	public Boolean getVote() {
		return vote;
	}


	public void setVote(Boolean vote) {
		this.vote = vote;
	}


	public LocalDateTime getCreateTime() {
		return createTime;
	}


	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "SnitchVote [userId=" + userId + ", snitchId=" + snitchId + ", vote=" + vote + ", createTime="
				+ createTime + ", note=" + note + "]";
	}
	
	
}
