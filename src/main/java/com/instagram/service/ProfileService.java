package com.instagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.config.persistence.HibernateConfig;

@Service
@Transactional
public class ProfileService {
	private HibernateConfig hibernateConfig;

	public ProfileService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}
}
