package com.instagram.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.instagram.enums.Role;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password", length = 512)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	@Column(name = "fullName")
	private String fullName;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Column(name = "dob")
	private LocalDate dob;
	@Column(name = "active_status")
	private Boolean activeStatus;
    @Column(name = "e_date")
    private String entryDate;
    @Column(name = "u_date")
    private String updateDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Boolean getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", fullName="
				+ fullName + ", email=" + email + ", gender=" + gender + ", dob=" + dob + ", activeStatus="
				+ activeStatus + ", entryDate=" + entryDate + ", updateDate=" + updateDate + "]";
	}
	public User(long id, String username, String password, Role role, String fullName, String email, String gender,
			LocalDate dob, Boolean activeStatus, String entryDate, String updateDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullName = fullName;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.activeStatus = activeStatus;
		this.entryDate = entryDate;
		this.updateDate = updateDate;
	}
	public User() {
		super();
	}
	
	
}
