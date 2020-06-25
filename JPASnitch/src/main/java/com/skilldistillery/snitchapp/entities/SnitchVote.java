package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Table (name="snitch_vote")
@Entity
public class SnitchVote {    
	
	@EmbeddedId
	private SnitchVoteId id;
	
	private Boolean vote;
	
	@CreationTimestamp
	@Column (name= "create_time")
	private LocalDateTime createTime;
	
	private String note;
	
	@MapsId (value = "userId")
	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;
	
	@MapsId (value = "snitchId")
	@ManyToOne
	@JoinColumn (name = "snitch_id")
	
	private Snitch snitch;
	
	//methods
	
	public SnitchVote() {
		super();
	}
	public SnitchVote(SnitchVoteId id, User user, Snitch snitch, Boolean vote, LocalDateTime createTime, String note) {
		super();
		this.id = id;
		this.user = user;
		this.snitch = snitch;
		this.vote = vote;
		this.createTime = createTime;
		this.note = note;
	}
	public SnitchVoteId getId() {
		return id;
	}
	public void setId(SnitchVoteId id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Snitch getSnitch() {
		return snitch;
	}
	public void setSnitch(Snitch snitch) {
		this.snitch = snitch;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SnitchVote other = (SnitchVote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SnitchVote [id=" + id + ", user=" + user + ", snitch=" + snitch + ", vote=" + vote + ", createTime="
				+ createTime + ", note=" + note + "]";
	}
}