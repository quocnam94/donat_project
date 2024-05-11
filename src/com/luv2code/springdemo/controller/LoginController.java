package com.luv2code.springdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.luv2code.springdemo.entity.Login;
import com.luv2code.springdemo.service.UserService;

@Controller
@RequestMapping("/landing")
public class LoginController {
	 
//	logic to invalid for Space character
	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@Autowired
	private UserService userService;
	
//	show login form
	@RequestMapping("/login")
	public String login (Model theModel) {
		theModel.addAttribute("login", new Login());
		return "login";
	}
//	logic to define email, pass, role and active status of the user
	@RequestMapping("/processLogin")
	public String processLogin(
			@Valid @ModelAttribute("login") Login theLogin,
			BindingResult theBindingResult, Model theModel) {
		if (theBindingResult.hasErrors()) {
			return "login";
		}
		String email = theLogin.getEmail();
		String password = theLogin.getPassword();
		String roleId = theLogin.getRoleId();
		String status = theLogin.getStatus();
		if (userService.isValidUser(email, password, roleId, status)) {
			int userId = userService.getUserIdByEmail(email);
			theModel.addAttribute("userId", userId);
		    return "redirect:/landing/listDonatForUser";
		}
		if (userService.isInactiveUser(email, password, roleId, status)) {
			return "login-fail-inactive";
		}
		if (userService.isValidAdmin(email, password, roleId)) {
		    return "home-admin";
		}
		return "login-fail";
	}
}
