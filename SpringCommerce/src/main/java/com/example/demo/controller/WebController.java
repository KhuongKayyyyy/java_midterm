package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import com.example.demo.repository.BooksRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class WebController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;
	@RequestMapping("/signup")
	public String signup(Model model) {
		session.setAttribute("messUserExist", "");
		model.addAttribute("user", new User());
		return "signup";
	}

	@GetMapping("/create-account")
	public String signup1(Model model) {
		model.addAttribute("user", new User());
		return "/signup";
	}

	@PostMapping("/create-account")
	public String createAccount(@ModelAttribute User user
			, Model model) {

		if(userExist(user)) {
			return "/signup";
		}
		else {
			user.setEnable(true);
			user.setRole("USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			userRepository.save(user);
			session.setAttribute("username", "Welcome "+user.getName());

			return "users/home";
		}
	}
	
	public boolean userExist(User user) {
		User u = userRepository.findUserByUsername(user.getUsername());
		System.out.println(user);
		if(u != null) {
			session.setAttribute("messUserExist", "This username had exist!");
			return true;
		}
		else {
			session.setAttribute("messUserExist", "");
			return false;
		}
	}

	@RequestMapping("/")
	public String index() {
		return "users/home";
	}
	@RequestMapping("/login")
	public String login(){
		return "signin";
	}
//
//	@RequestMapping("/403")
//	@ResponseBody
//	public String none_access() {
//		return "<h1>403 None</h1>";
//	}
//
    @GetMapping("/home")
    public String homepage() {
        return "home";
    }
}
