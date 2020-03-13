package com.instagram.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_postComment")
public class PostComment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "post_id", nullable = false)
	private ImageTest postId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@Column(name = "commentContent")
	private String commentContent;

	@Column(name = "e_date")
	private LocalDateTime entryDate;

	@Column(name = "u_date")
	private LocalDateTime updateDate;
	
	@Column(name = "is_delete")
	private Boolean isDelete;

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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public PostComment(Long id, ImageTest postId, User userId, String commentContent, LocalDateTime entryDate,
			LocalDateTime updateDate, Boolean isDelete) {
		super();
		this.id = id;
		this.postId = postId;
		this.userId = userId;
		this.commentContent = commentContent;
		this.entryDate = entryDate;
		this.updateDate = updateDate;
		this.isDelete = isDelete;
	}

	public PostComment() {
		super();
	}

	@Override
	public String toString() {
		return "PostComment [id=" + id + ", postId=" + postId + ", userId=" + userId + ", commentContent="
				+ commentContent + ", entryDate=" + entryDate + ", updateDate=" + updateDate + ", isDelete=" + isDelete
				+ "]";
	}

	
}
