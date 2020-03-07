package com.instagram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.model.Team;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long>{
	@Query(value = "select *" +
			" from tbl_team t" +
			" where t.name like %:keyword%", nativeQuery = true)
	List<Team> getTeamsByQueryString(@Param("keyword") String query);
}
