package com.luv2code.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.DonatDAO;
import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Service
public class DonatServiceImpl implements DonatService {

	@Autowired
	private DonatDAO donatDAO;
	
	@Override
	@Transactional
	public void saveDonat(Donat theDonat) {
		donatDAO.saveDonat(theDonat);
	}

	@Override
	@Transactional
	public Donat getDonat(int theId) {
		return donatDAO.getDonat(theId);
	}

	@Override
	@Transactional
	public void deleteDonat(int theId) {
		donatDAO.deleteDonat(theId);
	}

	@Override
	@Transactional
	public String changeStatusDonat(int theId) {
		return donatDAO.changeStatusDonat(theId);
	}

	@Override
	@Transactional
	public Page<Donat> getDonats(Pageable pageable) {
		return donatDAO.findAll(pageable);
	}

	@Override
	@Transactional
	public Page<Donat> searchDonats(String theSearch, Pageable pageable) {
		return donatDAO.searchDonats(theSearch, pageable);
	}

}
