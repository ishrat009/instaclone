package com.instagram.dto;

import java.util.ArrayList;
import java.util.List;

import com.instagram.model.PostComment;
import com.instagram.model.PostLike;

public class PostDto {
	private Long id;
	private String caption;
	private String userName;
	private String location;
	private String entryDate;
	private String logo;
	private List<PostComment> comments = new ArrayList<>();
	private List<PostLike> likes = new ArrayList<>();
	private Long totalLike;
	private Long totalComment;
	private Boolean isLiked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
	public Long getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(Long totalLike) {
		this.totalLike = totalLike;
	}
	public Long getTotalComment() {
		return totalComment;
	}
	public void setTotalComment(Long totalComment) {
		this.totalComment = totalComment;
	}
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	
	
}
