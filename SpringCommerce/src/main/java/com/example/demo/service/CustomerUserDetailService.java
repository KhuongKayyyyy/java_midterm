package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);

		session.setAttribute("username", "Welcome "+user.getName());
		session.setAttribute("cart", new ArrayList<Books>());
		session.setAttribute("quantity", new ArrayList<Integer>());
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		 GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		 grantList.add(authority);
		    
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantList);

		return userDetails;
        
	}

}
