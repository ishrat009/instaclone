package com.instagram.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_player")
public class Player implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	@ManyToOne
	private Team team;
	@Column(name = "name")
	private String name;
	@Column(name = "role")
	private String role;
	@Column(name = "age")
	private int age;
	@Column(name = "dob")
	private LocalDate dob;
//	private Timestamp dob;
	@Column(name = "active_status")
	private boolean activeStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", team=" + team + ", name=" + name + ", role=" + role + ", age=" + age + ", dob="
				+ dob + ", activeStatus=" + activeStatus + "]";
	}
	public Player(long id, Team team, String name, String role, int age, LocalDate dob, boolean activeStatus) {
		super();
		this.id = id;
		this.team = team;
		this.name = name;
		this.role = role;
		this.age = age;
		this.dob = dob;
		this.activeStatus = activeStatus;
	}
	public Player() {
		super();
	}

	
	
}
