package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

public interface UserDAO extends PagingAndSortingRepository<User, Integer>  {
	
	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);

	public String changeStatusUser(int theId);

	public boolean isValidUser(String email, String password, String roleId, String status);

	public boolean isValidAdmin(String email, String password, String roleId);

	public int getUserIdByEmail(String email);

	public boolean isInactiveUser(String email, String password, String roleId, String status);

	public Page<User> findAll(Pageable pageable);
	
	public Page<User> searchUsers (String theSearch, Pageable pageable);

}
