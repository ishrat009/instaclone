package com.instagram.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.dto.PostCommentDto;
import com.instagram.dto.TeamDto;
import com.instagram.model.ImageTest;
import com.instagram.model.PostComment;
import com.instagram.repositories.PostCommentRepository;
import com.instagram.repositories.TestRepository;
import com.instagram.repositories.UserRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;

	public void add(ImageTest test) {
		testRepository.save(test);
	}

	public Page<ImageTest> getAll(String searchText, int pageIndex, int rows, String sort) {
		Pageable pageWithElements;
		if (sort.equals("NA")) {
			pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("name").ascending());
		} else {
			pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("name").descending());
		}
		Page<ImageTest> test = testRepository.findByNameContaining(searchText, pageWithElements);

		// Page<ImageTest>
		// test=testRepository.findByUserName(searchText,pageWithElements);
		return test;
	}

	public List<ImageTest> getAll() {
		return testRepository.findAll();
	}

	
	  public List<ImageTest> getPostsById(Long id){
		  return testRepository.findByUser_id(id); 
	  }
	 

	 
	public PostComment insertComment(PostCommentDto postCmnDto) {
		LocalDateTime entry_date = LocalDateTime.now();
		System.out.println(entry_date);
		System.out.println(postCmnDto.getPostId());
		System.out.println(postCmnDto.getUserId());
		var postId = testRepository.findById(postCmnDto.getPostId()).get();
		var userId = userRepository.findById(postCmnDto.getUserId()).get();
		PostComment postcomnt = new PostComment();
		postcomnt.setUserId(userId);
		postcomnt.setPostId(postId);
		postcomnt.setEntryDate(entry_date);
		postcomnt.setIsDelete(true);
		postcomnt.setCommentContent(postCmnDto.getCommentContent());

	//	System.out.println(postId);
		
		postCommentRepository.save(postcomnt);
		//System.out.println(postcomnt);
		return postcomnt;

	}

}
