package com.instagram.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.model.PostComment;

@Repository
@Transactional
public interface PostCommentRepository  extends JpaRepository<PostComment, Long>{

}
