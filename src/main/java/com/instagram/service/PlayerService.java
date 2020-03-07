package com.instagram.service;


import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.config.persistence.HibernateConfig;
import com.instagram.dto.PlayerDto;
import com.instagram.exceptions.ResourceNotFoundException;
import com.instagram.model.Player;

@Service
public class PlayerService {
	@Autowired
	private TeamService teamService;
	private HibernateConfig hibernateConfig;

	public PlayerService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}
	@Transactional
	public void add(PlayerDto playerDto) {
		
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		Player playerEntity = new Player();
	    BeanUtils.copyProperties(playerDto,playerEntity);
		try {
			session.save(playerEntity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}
	public List<Player> getAll() {
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Player> sc = cb.createQuery(Player.class);
		Root<Player> root = sc.from(Player.class);
		sc.select(root);
		sc.where(cb.isTrue(root.get("activeStatus")));
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
		var player_list = query.getResultList();
		return player_list;
	}

	public Player getById(long playerId) {

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Player> sc = cb.createQuery(Player.class);
		Root<Player> root = sc.from(Player.class);
		sc.where(cb.equal(root.get("id"), playerId));
		
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
		var player_list = query.getResultList();

		return Optional.ofNullable(player_list.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Player Not Found With This Id"));
	}
	 public PlayerDto  getPlayerById(long playerId){
	        var session = hibernateConfig.getSession();
	        var transaction = session.getTransaction();
	        if (!transaction.isActive()){
	            transaction = session.beginTransaction();
	        }

	        CriteriaBuilder cb = session.getCriteriaBuilder();
	        CriteriaQuery<Player> sc = cb.createQuery(Player.class);
	        Root<Player> root = sc.from(Player.class);
	        sc.where(cb.equal(root.get("id"),playerId));
	        var query = session.createQuery(sc);
	        PlayerDto playerDto = new PlayerDto();
	        try {
	        	Player player = query.getSingleResult();
	            if (player == null){
	                throw new ResourceNotFoundException("Player Not found with this Id");
	            }
	            BeanUtils.copyProperties(player,playerDto);
	        }catch (HibernateException e){
	            e.printStackTrace();
	        }

	        return playerDto;
	    }
	@Transactional
	public void edit(PlayerDto playerDto) {
		//var team = teamService.getByName(player.getTeam().getName());
		//player.setTeam(team);
		
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		Player player = new Player();
		BeanUtils.copyProperties(playerDto, player);
		var team = teamService.getByName(player.getTeam().getName());
		player.setTeam(team);
		try {
			session.update(player);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void deactive(long playerId) {
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		if (!transection.isActive()) {
			transection = session.beginTransaction();
		}
		var player = this.getById(playerId);
		player.setActiveStatus(false);
		try {
			session.update(player);
			transection.commit();
		} catch (HibernateException e) {
			if (transection != null) {
				transection.rollback();
			}
			e.printStackTrace();
		}
	}
}
