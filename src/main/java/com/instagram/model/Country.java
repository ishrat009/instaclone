package com.instagram.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "tbl_country")
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name = "country_code", unique = true, nullable = false, updatable = false)
	private String countryCode;
	@Column(name = "country_name", nullable = false)
	private String countryName;
	@Column(name = "active_status")
	private boolean activeStatus;


	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Country() {
		super();
	}

	public Country(long id, String countryCode, String countryName) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}
}
