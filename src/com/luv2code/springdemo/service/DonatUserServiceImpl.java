package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.DonatUserDAO;
import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Service
public class DonatUserServiceImpl implements DonatUserService {
	
	@Autowired
	private DonatUserDAO donatUserDAO;

	@Override
	@Transactional
	public void saveDonatForUser(DonatUser theDonatUser) {
		donatUserDAO.saveDonatForUser(theDonatUser);
	}

	@Override
	@Transactional
	public List<DonatUser> getUserByDonatId(int theId) {
		return donatUserDAO.getUserByDonatId(theId);
	}

	@Override
	@Transactional
	public Double sumMoney(int theId) {
		return donatUserDAO.sumMoney(theId);
	}

	@Override
	@Transactional
	public List<DonatUser> getDonatByUserId(int theId) {
		return donatUserDAO.getDonatByUserId(theId);
	}

	@Override
	@Transactional
	public Double sumMoneyByUser(int theId) {
		return donatUserDAO.sumMoneyByUser(theId);
	}

	@Override
	@Transactional
	public Page<DonatUser> getUserByDonatId(int theId, Pageable pageable) {
		return donatUserDAO.getUserByDonatId(theId, pageable);
	}

	@Override
	@Transactional
	public Page<DonatUser> searchUserDonated(int theId, String theSearch, Pageable pageable) {
		return donatUserDAO.searchUserDonated(theId, theSearch, pageable);
	}
}
