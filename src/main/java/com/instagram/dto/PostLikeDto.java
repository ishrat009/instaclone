package com.instagram.dto;

import com.instagram.model.ImageTest;
import com.instagram.model.User;

public class PostLikeDto {
	private Long id;
	private ImageTest postId;
	private User userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ImageTest getPostId() {
		return postId;
	}
	public void setPostId(ImageTest postId) {
		this.postId = postId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
}
