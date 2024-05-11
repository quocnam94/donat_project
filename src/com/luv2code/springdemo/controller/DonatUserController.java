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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.entity.UserDonationDto;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;
import com.luv2code.springdemo.service.DonatService;
import com.luv2code.springdemo.service.DonatUserService;
import com.luv2code.springdemo.service.UserService;

@Controller
@RequestMapping("/landing")
public class DonatUserController {
	
//	logic to invalid for Space character
	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	@Autowired
	private DonatService donatService;
	@Autowired
	private UserService userService;
	@Autowired
	private DonatUserService donatUserService;
	
//	show the donations for the user and pageable
	@GetMapping("/listDonatForUser")
	public String listDonatForUser(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam ("userId") int userId,
            Model theModel) {
		Pageable pageable = new Pageable(page, size);
		Page<Donat> theDonats = donatService.getDonats(pageable);
		theModel.addAttribute("donats", theDonats);
		theModel.addAttribute("userId", userId);
		return "home-user";
	}
	
//	show the detail of the donatione for the user
	@RequestMapping("/detailDonatForUser")
	public String detailDonatForUser(@RequestParam ("donatId") int theId, 
			@RequestParam ("userId") int userId,
			Model theModel) {
		Donat theDonat = donatService.getDonat(theId);
		User theUser = userService.getUser(userId);
		List<DonatUser> theDonatUsers = donatUserService.getUserByDonatId(theId);
		Double moneyDonated = donatUserService.sumMoney(theId);
		theModel.addAttribute("donat", theDonat);
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("donatUsers", theDonatUsers);
		theModel.addAttribute("moneyDonated", moneyDonated);
		theDonat.setMoneyDonated(moneyDonated);
		donatService.saveDonat(theDonat);
		return "detail-donat-for-user";
	}

//	show the form of the donat for the user
	@RequestMapping("/showFormForDonat")
	public String showFormForDonat(@RequestParam ("donatId") int theId,
			@RequestParam ("userId") int userId,
			Model theModel) {
		UserDonationDto donate = new UserDonationDto();
		Donat theDonat = donatService.getDonat(theId);
		User theUser = userService.getUser(userId);
		theModel.addAttribute("donate", donate);
		theModel.addAttribute("donat", theDonat);
		theModel.addAttribute("user", theUser);
		return "donat-form";
	}
	
//	logic to save the donation of the user
	@RequestMapping("/saveDonatForUser")
	public String saveDonatForUser (@Valid
			@ModelAttribute ("donate") UserDonationDto theUserDonationDto, 
			BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "donat-form";
		}
		else {
		DonatUser donatUser = new DonatUser();
		donatUser.setName(theUserDonationDto.getName());
		donatUser.setMoney(theUserDonationDto.getMoney());
		donatUser.setText(theUserDonationDto.getText());
		donatUser.setTheDonat(donatService.getDonat(theUserDonationDto.getDonatId()));
		donatUser.setTheUser(userService.getUser(theUserDonationDto.getUserId()));
		donatUserService.saveDonatForUser(donatUser);
	    return "redirect:/landing/listDonatForUser?userId=" + theUserDonationDto.getUserId();
		}
	}
}
