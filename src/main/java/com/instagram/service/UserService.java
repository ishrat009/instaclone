package com.instagram.service;


import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.instagram.dto.UserDto;
import com.instagram.enums.Role;
import com.instagram.exceptions.ResourceAlreadyExistsException;
import com.instagram.repositories.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var userFromDb = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with this email address."));
        List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());

        authorities.add((GrantedAuthority) () -> userFromDb.getRole().name());

        return new User(userFromDb.getUsername(), userFromDb.getPassword(), authorities);
    }
    
    public void addUser(UserDto userDto) {
		LocalDateTime entry_date = LocalDateTime.now();
		checkUserInList(userDto);
		var userEntity = new com.instagram.model.User();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setActiveStatus(true);
		userEntity.setRole(Role.ROLE_ADMIN);
		//userEntity.setEntryDate(entryDate);
		userRepository.save(userEntity);
	}

	private void checkUserInList(UserDto userDto) {

		var username = userRepository.findByusername(userDto.getUsername());
		var userEmail = userRepository.findByEmail(userDto.getEmail());

		if (username != null) {
			throw new ResourceAlreadyExistsException("UserName Already exists");
		}
		if (userEmail != null) {
			throw new DuplicateRequestException("Email Already Used");
		}

	}
	
	  public Optional<com.instagram.model.User> getUserByUserId(Long id) { 
		  return userRepository.findById(id); 
		  } 
	  public Optional<com.instagram.model.User> getUserByUserName(String s) { 
		  return userRepository.findByUsername(s); 
		  }
	  public com.instagram.model.User getUserByName(String s) { 
		  return userRepository.findByusername(s); 
		  }
}
