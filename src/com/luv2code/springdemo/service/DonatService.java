package com.luv2code.springdemo.service;

import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Service
public interface DonatService {
	public void saveDonat (Donat theDonat);
	public Donat getDonat(int theId);
	public void deleteDonat(int theId);
	public String changeStatusDonat(int theId);
	public Page<Donat> getDonats(Pageable pageable);
	public Page<Donat> searchDonats(String theSearch, Pageable pageable);

}
