package com.skilldistillery.snitchapp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;

	@JsonIgnore
	@OneToMany (mappedBy= "category")
	private List<Alert> alerts;
	
	@JsonIgnore
	@OneToMany (mappedBy= "category")
	private List<Snitch> snitches;
	
	//methods
	
	public Category() {
		super();
	}

	public Category(int id, String name, String description, List<Alert> alerts, List<Snitch> snitches) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.alerts = alerts;
		this.snitches = snitches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public List<Snitch> getSnitches() {
		return snitches;
	}

	public void setSnitches(List<Snitch> snitches) {
		this.snitches = snitches;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", alerts=" + alerts
				+ ", snitches=" + snitches + "]";
	}

	
	
}
