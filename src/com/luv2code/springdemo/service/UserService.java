package com.luv2code.springdemo.service;

import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Service
public interface UserService {
	public void saveUser (User theUser);
	public User getUser(int theId);
	public void deleteUser(int theId);
	public String changeStatusUser(int theId);
	public Page<User> searchUsers(String theSearch, Pageable pageable);
	public boolean isValidUser(String email, String password, String roleId, String status);
	public boolean isValidAdmin(String email, String password, String roleId);
	public int getUserIdByEmail(String email);
	public boolean isInactiveUser(String email, String password, String roleId, String status);
	public Page<User> getUsers(Pageable pageable);
}
