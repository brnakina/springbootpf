package com.sbksystem.springbootpf.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sbksystem.springbootpf.entity.User;
import com.sbksystem.springbootpf.service.UserService;

import jakarta.validation.Valid;

// Controllerアノテーションを付与するとreturnした文字列に応じてsrc/main/resources/templates内に配置したファイルがHTTPレスポンスとして返る
@Controller
public class WebTest {
	
    @Autowired
    private UserService userService;

	@GetMapping("/index")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/users/list")
	public String getUsersList(Model model) {
		model.addAttribute("users", userService.getAll());
		
		return "userList";
	}
	
	@GetMapping("/users/register")
	public String getUserRegister(User user) {
		return "userRegister";
	}
	
	@PostMapping("/users/register")
    public String saveUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userRegister";
        }
        
        
        Date now = new Date();
        user.setCreated(now);
        user.setCreated_at(now);
        user.setUpdated_at(now);
        
        userService.save(user);
        model.addAttribute("users", userService.getAll());
        
        return "userList";
    }}
