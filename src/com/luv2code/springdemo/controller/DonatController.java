package com.luv2code.springdemo.controller;

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

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;
import com.luv2code.springdemo.service.DonatService;
import com.luv2code.springdemo.service.DonatUserService;

@Controller
@RequestMapping("/landing")
public class DonatController {
//	logic to invalid for Space character
	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@Autowired
	private DonatService donatService;
	
	@Autowired
	private DonatUserService donatUserService;
	
//	show donations list and pageable
	@GetMapping("/listDonat")
	public String listDonats(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model theModel) {
		Pageable pageable = new Pageable(page, size);
		Page<Donat> theDonats = donatService.getDonats(pageable);
		theModel.addAttribute("donats", theDonats);
		return "list-donats";
	}
	
//	show form to add the donation
	@GetMapping("/addDonat")
	public String addDonat (Model theModel) {
		Donat theDonat = new Donat();
		theModel.addAttribute("donat", theDonat);
		return "add-donat";
	}
	
//	logic to save the new donation
	@PostMapping("/saveDonat")
	public String saveDonat (@Valid @ModelAttribute ("donat") Donat theDonat,
		BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "add-donat";
		}
		else {
			donatService.saveDonat(theDonat);
			return "redirect:/landing/listDonat";
		}
	}
//	show form to update the donation
	@RequestMapping("/showFormForUpdateDonat")
	public String showFormForUpdateDonat(@RequestParam ("donatId") int theId, Model theModel) {
		Donat theDonat = donatService.getDonat(theId);
		theModel.addAttribute("donat", theDonat);
		return "update-form-donat";
	}
//	logic to save the update of donation
	@PostMapping("/saveDonatInUpdate")
	public String saveDonatInUpdate (@Valid @ModelAttribute ("donat") Donat theDonat,
		BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "update-form-donat";
		}
		else {
			donatService.saveDonat(theDonat);
			return "redirect:/landing/listDonat";
		}
	}
//	show form for the detail of the donation
	@RequestMapping("/detailDonat")
	public String showDetailDonat(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam ("donatId") int theId,
            Model theModel) {
		Donat theDonat = donatService.getDonat(theId);
		Pageable pageable = new Pageable(page, size);
		Page<DonatUser> theDonatUsers = donatUserService.getUserByDonatId(theId, pageable);
		theModel.addAttribute("donat", theDonat);
		theModel.addAttribute("donatUsers", theDonatUsers);
		return "detail-donat";
	}
//	logic to search the user donated
	@GetMapping("/searchUserDonated")
	public String searchUserDonated(@RequestParam ("donatId") int theId,
	        @RequestParam("theSearch") String theSearch,
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "10") int size,
	        Model theModel) {
		Donat theDonat = donatService.getDonat(theId);
	    Pageable pageable = new Pageable(page, size);
	    Page<DonatUser> donatUsers = donatUserService.searchUserDonated(theId, theSearch, pageable);
		theModel.addAttribute("donatUsers", donatUsers);
		theModel.addAttribute("donat", theDonat);
		return "detail-donat";
	}
//	logic to delete the donation (still in db)
	@PostMapping("/deleteDonat")
	public String deleteDonat(@RequestParam ("donatId") int theId){
		donatService.deleteDonat(theId);
		return "redirect:/landing/listDonat";
	}
//	logic to search the donations
	@GetMapping("/searchDonat")
	public String searchDonat(
	        @RequestParam("theSearch") String theSearch,
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "10") int size,
	        Model theModel) {
	    Pageable pageable = new Pageable(page, size);
	    Page<Donat> theDonats = donatService.searchDonats(theSearch, pageable);
	    theModel.addAttribute("donats", theDonats);
	    return "list-donats";
	}
//	logic to change the status of the donations
	@PostMapping("/changeStatusDonat")
	public String changeStatusDonat (@RequestParam ("donatId") int theId, Model theModel) {
		donatService.changeStatusDonat(theId);
		return "redirect:/landing/listDonat";
	}
}
