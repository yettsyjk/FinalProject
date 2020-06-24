package com.skilldistillery.snitchapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "pin_name")
	private String pinName;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private Integer zip;

	@OneToMany (mappedBy= "address")
	private List<Snitch> snitches;
	
	//methods
	
	
	public Address() {
		super();
	}

	public Address(int id, String pinName, String street, String city, String state, Integer zip,
			List<Snitch> snitches) {
		super();
		this.id = id;
		this.pinName = pinName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.snitches = snitches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPinName() {
		return pinName;
	}

	public void setPinName(String pinName) {
		this.pinName = pinName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", pinName=" + pinName + ", street=" + street + ", city=" + city + ", state="
				+ state + ", zip=" + zip + ", snitches=" + snitches + "]";
	}

	
	
}
