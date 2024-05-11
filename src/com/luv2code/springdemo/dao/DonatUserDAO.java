package com.luv2code.springdemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Repository
public interface DonatUserDAO{

	public void saveDonatForUser(DonatUser theDonatUser);

	public List<DonatUser> getUserByDonatId(int theId);

	public Double sumMoney(int theId);

	public List<DonatUser> getDonatByUserId(int theId);

	public Double sumMoneyByUser(int theId);
	
	public Page<DonatUser> findAll(int theId, Pageable pageable);

	public Page<DonatUser> getUserByDonatId(int theId, Pageable pageable);

	public Page<DonatUser> searchUserDonated(int theId, String theSearch, Pageable pageable);
	
	public Page<DonatUser> findAll(int theId, String theSearch, Pageable pageable);


}
