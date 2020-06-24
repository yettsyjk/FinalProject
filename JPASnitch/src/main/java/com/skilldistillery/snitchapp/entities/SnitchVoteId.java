package com.skilldistillery.snitchapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class SnitchVoteId implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	@Column(name = "user_id")
	private int userId;

	@Column(name = "snitch_id")
	private int snitchId;

	public int getUserId() {
		return userId;
	}

	public SnitchVoteId() {
		super();
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSnitchId() {
		return snitchId;
	}

	public void setSnitchId(int snitchId) {
		this.snitchId = snitchId;
	}

	public SnitchVoteId(int userId, int snitchId) {
		super();
		this.userId = userId;
		this.snitchId = snitchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + snitchId;
		result = prime * result + userId;
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
		SnitchVoteId other = (SnitchVoteId) obj;
		if (snitchId != other.snitchId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SnitchVoteId [userId=" + userId + ", snitchId=" + snitchId + "]";
	}

}
