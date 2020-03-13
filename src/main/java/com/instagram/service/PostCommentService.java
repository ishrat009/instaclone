package com.instagram.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.instagram.config.persistence.HibernateConfig;
import com.instagram.exceptions.ResourceNotFoundException;
import com.instagram.model.ImageTest;

@Service
public class PostCommentService {
	private HibernateConfig hibernateConfig;
	
	public PostCommentService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}
	
	
	/*
	 * public List<Team> getAll() { CriteriaBuilder cb =
	 * hibernateConfig.getCriteriaBuilder(); CriteriaQuery<Team> cq =
	 * cb.createQuery(Team.class); Root<Team> root = cq.from(Team.class);
	 * cq.select(root); cq.where(cb.isTrue(root.get("activeStatus"))); List<Team>
	 * team_list =
	 * hibernateConfig.getSession().getEntityManagerFactory().createEntityManager()
	 * .createQuery(cq).getResultList(); return team_list;
	 * 
	 * } public Team getByName(String name) { CriteriaBuilder cb =
	 * hibernateConfig.getCriteriaBuilder(); CriteriaQuery<Team> cq =
	 * cb.createQuery(Team.class); Root<Team> root = cq.from(Team.class);
	 * cq.where(cb.equal(root.get("name"), name));
	 * cq.where(cb.isTrue(root.get("activeStatus"))); var result =
	 * hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().
	 * createQuery(cq) .getResultList();
	 * 
	 * return Optional.ofNullable(result.get(0)) .orElseThrow(() -> new
	 * ResourceNotFoundException("Team not found with this name")); }
	 */
	public ImageTest getById(long postId) {

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ImageTest> sc = cb.createQuery(ImageTest.class);
		Root<ImageTest> root = sc.from(ImageTest.class);
		sc.select(root);
		sc.where(
				cb.and(
						cb.equal(root.get("id"), postId),
						cb.isTrue(root.get("isDelete"))
				)
		);
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
		var post_list = query.getResultList();

		return Optional.ofNullable(post_list.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Post Not Found With This Id"));
	}

	/*
	 * public void edit(Team team) { var country =
	 * countryService.getCountryByCode(team.getCountry().getCountryCode());
	 * team.setCountry(country); var session = hibernateConfig.getSession(); var
	 * transaction = session.getTransaction(); if (!transaction.isActive()) {
	 * transaction = session.beginTransaction(); } // var country =
	 * countryService.getByCode(team.getCountry().getCountryCode());
	 * //team.setCountry(country); team.setActiveStatus(true); try {
	 * session.update(team); transaction.commit(); } catch (HibernateException e) {
	 * if (transaction != null) { transaction.rollback(); } e.printStackTrace(); } }
	 * 
	 * public void deactive(long teamId) { var session =
	 * hibernateConfig.getSession(); var transection = session.beginTransaction();
	 * if (!transection.isActive()) { transection = session.beginTransaction(); }
	 * var team = this.getById(teamId); team.setActiveStatus(false);
	 * 
	 * //this.edit(team); try { session.update(team); transection.commit(); } catch
	 * (HibernateException e) { if (transection != null) { transection.rollback(); }
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
}
