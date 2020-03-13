package com.instagram.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.model.ImageTest;


@Repository
@Transactional
public interface TestRepository extends  PagingAndSortingRepository<ImageTest, Long>{
	//ImageTest findByid(Long id);
	Optional<ImageTest> findById(Long postId);
	
	List<ImageTest> findByName(String name,Pageable pageable);
	List<ImageTest> findAll();
	Page<ImageTest> findByNameContaining(String name,Pageable pageable);	
	Page<ImageTest> findByUserName(String userName,Pageable pageable);	
	List<ImageTest> findByUser_id(Long id);
	
	  @Query(value = "select *" + " from tbl_test t" +
	  " where t.user_name like %:keyword%", nativeQuery = true) 
	  List<ImageTest> getPostsByQueryString(@Param("keyword") String query);
	 
}
