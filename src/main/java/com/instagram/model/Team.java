package com.instagram.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_team")
public class Team implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private long id;
	@OneToOne
	private Country country;
	@Column(name = "name")
	private String name;
	@Column(name = "active_status")
	private boolean activeStatus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", country=" + country + ", name=" + name + ", activeStatus=" + activeStatus + "]";
	}
	public Team(long id, Country country, String name, boolean activeStatus) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
		this.activeStatus = activeStatus;
	}
	public Team() {
		super();
	}
	
	
	
}
