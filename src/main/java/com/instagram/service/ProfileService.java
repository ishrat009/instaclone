package com.instagram.service;

import com.instagram.config.persistence.HibernateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final HibernateConfig hibernateConfig;

    @Autowired
    public ProfileService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

}  // End of Class
