package com.luv2code.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.UserDAO;
import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
		
	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
	}

	@Override
	@Transactional
	public String changeStatusUser(int theId) {
		return userDAO.changeStatusUser(theId);
	}

	@Override
	@Transactional
	public Page<User> searchUsers(String theSearch, Pageable pageable) {
		return userDAO.searchUsers(theSearch, pageable);
	}

	@Override
	@Transactional
	public boolean isValidUser(String email, String password, String roleId, String status) {
		return userDAO.isValidUser(email, password, roleId, status);
	}

	@Override
	@Transactional
	public boolean isValidAdmin(String email, String password, String roleId) {
		return userDAO.isValidAdmin(email, password, roleId);
	}

	@Override
	@Transactional
	public int getUserIdByEmail(String email) {
		return userDAO.getUserIdByEmail(email);
	}

	@Override
	@Transactional
	public boolean isInactiveUser(String email, String password, String roleId, String status) {
		return userDAO.isInactiveUser(email, password, roleId, status);
	}
	
	@Override
	@Transactional
	public Page<User> getUsers(Pageable pageable) {
        return userDAO.findAll(pageable);
    }

}
