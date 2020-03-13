package com.instagram.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_test")
public class ImageTest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "is_delete")
	private Boolean isDelete;

	@Column(name = "e_date")
	private String entryDate;

	@Column(name = "u_date")
	private String updateDate;

	@Column(name = "logo", nullable = false)
	private String logo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postId", cascade = CascadeType.ALL)
	private List<PostComment> comments = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postId", cascade = CascadeType.ALL)
	private List<PostLike> likes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	public List<PostLike> getLikes() {
		return likes;
	}

	public void setLikes(List<PostLike> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "ImageTest [id=" + id + ", name=" + name + ", userName=" + userName + ", user=" + user + ", location="
				+ location + ", isDelete=" + isDelete + ", entryDate=" + entryDate + ", updateDate=" + updateDate
				+ ", logo=" + logo + ", comments=" + comments + ", likes=" + likes + "]";
	}

	public ImageTest(Long id, String name, String userName, User user, String location, Boolean isDelete,
			String entryDate, String updateDate, String logo, List<PostComment> comments, List<PostLike> likes) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.user = user;
		this.location = location;
		this.isDelete = isDelete;
		this.entryDate = entryDate;
		this.updateDate = updateDate;
		this.logo = logo;
		this.comments = comments;
		this.likes = likes;
	}

	public ImageTest() {
		super();
	}

	

}
