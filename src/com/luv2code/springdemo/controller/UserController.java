package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;
import com.luv2code.springdemo.service.DonatUserService;
import com.luv2code.springdemo.service.UserService;

@Controller
@RequestMapping("/landing")
public class UserController {
	
//	logic to invalid for Space character
	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DonatUserService donatUserService;
	
//	show list of the users and pageable
	@GetMapping("/list")
	public String listUsers(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model theModel) {
		Pageable pageable = new Pageable(page, size);
		Page<User> theUsers = userService.getUsers(pageable);
		theModel.addAttribute("users", theUsers);
		return "list-users";
	}
//	show the form to add the user
	@GetMapping("/addUser")
	public String addUser (Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "add-user";
	}
//	logic to save the new user
	@PostMapping("/saveUser")
	public String saveUser (@Valid @ModelAttribute ("user") User theUser, 
		BindingResult theBindingResult, Model theModel){
		if (theBindingResult.hasErrors()) {
			return "add-user";
		}
		else {
			userService.saveUser(theUser);
			return "redirect:/landing/list";
		}
	}
//	show the form to update the user
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam ("userId") int theId, Model theModel) {
		User theUser = userService.getUser(theId);
		theModel.addAttribute("user", theUser);
		return "update-form";
	}
	
//	logic to save the update of user
	@PostMapping("/saveUpdateUser")
	public String saveUpdateUser (@Valid @ModelAttribute ("user") User theUser, 
		BindingResult theBindingResult, Model theModel){
		if (theBindingResult.hasErrors()) {
			return "update-form";
		}
		else {
			userService.saveUser(theUser);
			return "redirect:/landing/list";
		}
	}
	
//	show form for the detail of the user
	@RequestMapping("/detailUser")
	public String showDetailUser(@RequestParam ("userId") int theId, 
			Model theModel) {
		User theUser = userService.getUser(theId);
		List<DonatUser> theDonatUsers = donatUserService.getDonatByUserId(theId);
		Double moneyDonated = donatUserService.sumMoneyByUser(theId);
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("donatUsers", theDonatUsers);
		theModel.addAttribute("moneyDonated", moneyDonated);
		return "detail-user";
	}

//	logic to delete the user
	@PostMapping("/delete")
	public String delete(@RequestParam ("userId") int theId){
		userService.deleteUser(theId);
		return "redirect:/landing/list";
	}
//	logic to change the status of the user
	@PostMapping("/changeStatus")
	public String changeStatus (@RequestParam ("userId") int theId, Model theModel) {
		String theUser = userService.changeStatusUser(theId);
		theModel.addAttribute("user", theUser);
		return "redirect:/landing/list";
	}
//	logic to search the users
	@GetMapping("/search")
	public String search(
	        @RequestParam("theSearch") String theSearch,
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "10") int size,
	        Model theModel) {
	    Pageable pageable = new Pageable(page, size);
	    Page<User> theUsers = userService.searchUsers(theSearch, pageable);
		theModel.addAttribute("users", theUsers);
		return "list-users";
	}
}
