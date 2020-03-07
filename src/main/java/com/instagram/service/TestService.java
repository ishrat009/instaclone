package com.instagram.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.dto.TeamDto;
import com.instagram.model.ImageTest;
import com.instagram.repositories.TestRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestRepository testRepository;

	public void add(ImageTest test) {
		testRepository.save(test);
	}
	
	public Page<ImageTest> getAll(String searchText,int pageIndex,int rows,String sort) {
		Pageable pageWithElements;
		if(sort.equals("NA")) {			
			pageWithElements = PageRequest.of(pageIndex, rows,Sort.by("name").ascending());
		}else {			
			pageWithElements = PageRequest.of(pageIndex, rows,Sort.by("name").descending());	
		}		
	//	Page<ImageTest> test=testRepository.findByNameContaining(searchText,pageWithElements);		
		
		Page<ImageTest> test=testRepository.findByUserName(searchText,pageWithElements);		
		return test;
	}	
	public List<ImageTest> getAll() {
		return testRepository.findAll();
	}
	/*
	 * public List<ImageTest> posts(String query) { return
	 * testRepository.getPostsByQueryString(query); }
	 */
	
}
