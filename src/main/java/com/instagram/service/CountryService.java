package com.instagram.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.config.persistence.HibernateConfig;
import com.instagram.exceptions.ResourceAlreadyExistsException;
import com.instagram.exceptions.ResourceNotFoundException;
import com.instagram.model.Country;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class CountryService {

	private final HibernateConfig hibernateConfig;

	private static List<Country> countries = new ArrayList<Country>();

	@Autowired
	public CountryService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}

	/*
	 * @Transactional public void add(Country country) { var session =
	 * hibernateConfig.getSession(); var transaction = session.beginTransaction();
	 * country.setActiveStatus(true); session.save(country); transaction.commit(); }
	 * 
	 * public void checkInList(Country c) { if (countries.stream().filter(country ->
	 * country.getCountryCode().equals(c.getCountryCode())).findAny() .isPresent())
	 * { throw new ResourceAlreadyExistsException("Country already exists in list");
	 * } }
	 */
	@Transactional
	public void add(Country country) {
		var session = hibernateConfig.getSession();
		var transaction = session.beginTransaction();
		country.setActiveStatus(true);
		session.save(country);
		transaction.commit();
	}

	public void checkInList(Country c) {
		if (countries.stream().filter(country -> country.getCountryCode().equals(c.getCountryCode())).findAny()
				.isPresent()) {
			throw new ResourceAlreadyExistsException("Country already exists in list");
		}
	}

	public Country getCountryByCode(String countryCode) {
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.select(root);
		// cq.where(cb.isTrue(root.get("activeStatus")));
		cq.where(cb.equal(root.get("countryCode"), countryCode));
		//cq.where(cb.equal(root.get("activeStatus"), true));
		var result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq)
				.getResultList();
		return Optional.ofNullable(result.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Country not found with this code"));
	}

	public Country getByCode(String countryCode) {
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.where(cb.equal(root.get("countryCode"), countryCode));
		cq.where(cb.isTrue(root.get("activeStatus")));
		var result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq)
				.getResultList();
		return Optional.ofNullable(result.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Country not found with this code"));
	}

	/*
	 * public List<Country> getAll() { CriteriaBuilder cb =
	 * hibernateConfig.getCriteriaBuilder(); CriteriaQuery<Country> cq =
	 * cb.createQuery(Country.class); Root<Country> root = cq.from(Country.class);
	 * cq.select(root); List<Country> countries =
	 * hibernateConfig.getSession().getEntityManagerFactory().createEntityManager()
	 * .createQuery(cq).getResultList(); return countries; }
	 */
	public List<Country> getAll() {
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.select(root);
		cq.where(cb.isTrue(root.get("activeStatus")));
		List<Country> countries = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager()
				.createQuery(cq).getResultList();
		return countries;
	}

	/*
	 * public Country getById(long countryId) { CriteriaBuilder cb =
	 * hibernateConfig.getCriteriaBuilder(); CriteriaQuery<Country> cq =
	 * cb.createQuery(Country.class); Root<Country> root = cq.from(Country.class);
	 * cq.select(root);
	 * 
	 * cq.where(cb.and(cb.equal(root.get("id"), countryId),
	 * cb.isTrue(root.get("activeStatus")))); cq.where( cb.and(
	 * cb.equal(root.get("id"), countryId), cb.isTrue(root.get("activeStatus")) ) );
	 * 
	 * // cq.where(cb.equal(root.get("id"), countryId)); // Expression<Boolean> t =
	 * cb.literal(true); // cq.where(cb.equal(root.get("activeStatus"), t));
	 * 
	 * var result =
	 * hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().
	 * createQuery(cq) .getResultList(); if (result.size() > 0) return
	 * result.get(0); else throw new RuntimeException("No value present"); }
	 */
	public Country getById(long countryId) {
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.select(root);
		// cq.where(cb.and(cb.equal(root.get("id"), countryId),
		// cb.isTrue(root.get("activeStatus"))));
		cq.where(cb.and(cb.equal(root.get("id"), countryId), cb.isTrue(root.get("activeStatus"))));

		var result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq)
				.getResultList();
		if (result.size() > 0)
			return result.get(0);
		else
			throw new RuntimeException("No value present");
	}

	public void edit(Country country) {
		var session = hibernateConfig.getSession();
		var trans = session.getTransaction();
		if (!trans.isActive()) {
			trans = session.beginTransaction();
		}
		country.setActiveStatus(true);
		try {
			session.update(country);
			trans.commit();
		} catch (HibernateException e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
	}

	/*
	 * public void edit(Country country) { var session =
	 * hibernateConfig.getSession(); var trans = session.getTransaction();
	 * 
	 * if (!trans.isActive()) { trans = session.beginTransaction(); }
	 * 
	 * try { session.update(country); trans.commit();
	 * 
	 * } catch (HibernateException e) { if (trans != null) { trans.rollback(); }
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	public void delete(long countryId) {
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		if (!transection.isActive()) {
			transection = session.beginTransaction();
		}
		var country = this.getById(countryId);
		country.setActiveStatus(false);
		try {
			session.update(country);
			transection.commit();
		} catch (HibernateException e) {
			if (transection != null) {
				transection.rollback();
			}
			e.printStackTrace();
		}
	}
	/*
	 * public void delete(long countryId) { var session =
	 * hibernateConfig.getSession(); var transection = session.beginTransaction();
	 * if (!transection.isActive()) { transection = session.beginTransaction(); }
	 * var country = this.getById(countryId); country.setActiveStatus(false);
	 * this.edit(country); }
	 */
}
