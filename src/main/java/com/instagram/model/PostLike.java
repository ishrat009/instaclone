package com.instagram.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_postLikes")
public class PostLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private ImageTest postId;

    @OneToOne
    @JoinColumn(name = "user_id")
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

	@Override
	public String toString() {
		return "PostLike [id=" + id + ", postId=" + postId + ", userId=" + userId + "]";
	}

	public PostLike(Long id, ImageTest postId, User userId) {
		super();
		this.id = id;
		this.postId = postId;
		this.userId = userId;
	}

	public PostLike() {
		super();
	}


}
