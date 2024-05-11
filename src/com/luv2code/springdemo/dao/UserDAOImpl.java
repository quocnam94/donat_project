package com.luv2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Repository
public class UserDAOImpl implements UserDAO, PagingAndSortingRepository<User, Integer> {

	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public void saveUser(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(theUser);
	}

	@Override
	public User getUser(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, theId);
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("delete from User where id=:userId", User.class);
		theQuery.setParameter("userId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public String changeStatusUser(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, theId);
		if (theUser.getStatus().equals("Active")) {
			return theUser.setStatus("Inactive");
		} else {
			return theUser.setStatus("Active");
		}
	}
	
//	check email, pass, role and status from db
	@Override
	public boolean isValidUser(String email, String password, String roleId, String status) {
		try (Session currentSession = sessionFactory.openSession()) {
            Query<User> theQuery = currentSession.createQuery("from User u where u.email = :email "
            		+ "and u.password = :password "
            		+ "and u.roleId = :roleId "
            		+ "and u.status = :status", User.class);
            theQuery.setParameter("email", email);
            theQuery.setParameter("password", password);
            theQuery.setParameter("roleId", "User");
            theQuery.setParameter("status", "Active");
            User theUser = theQuery.uniqueResult();
            if (theUser == null) {
                return false;
            }
            currentSession.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}	

	@Override
	public boolean isValidAdmin(String email, String password, String roleId) {
		try (Session currentSession = sessionFactory.openSession()) {
            Query<User> theQuery = currentSession.createQuery("from User u where u.email = :email "
            		+ "and u.password = :password "
            		+ "and u.roleId = :roleId", User.class);
            theQuery.setParameter("email", email);
            theQuery.setParameter("password", password);
            theQuery.setParameter("roleId", "Admin");
            User theUser = theQuery.uniqueResult();
            if (theUser == null) {
                return false;
            }
            currentSession.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean isInactiveUser(String email, String password, String roleId, String status) {
		try (Session currentSession = sessionFactory.openSession()) {
            Query<User> theQuery = currentSession.createQuery("from User u where u.email = :email "
            		+ "and u.password = :password "
            		+ "and u.roleId = :roleId "
            		+ "and u.status = :status", User.class);
            theQuery.setParameter("email", email);
            theQuery.setParameter("password", password);
            theQuery.setParameter("roleId", "User");
            theQuery.setParameter("status", "Inactive");
            User theUser = theQuery.uniqueResult();
            if (theUser == null) {
                return false;
            }
            currentSession.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	@Override
	public int getUserIdByEmail(String email) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<User> query = currentSession.createQuery("from User where email = :email", User.class);
	    query.setParameter("email", email);
	    List<User> userList = query.list();
	    if (!userList.isEmpty()) {
	        User user = userList.get(0);
	        return user.getId();
	    } else {
	        return -1;
	    }
	}
	
	@Override
	public Page<User> searchUsers(String theSearch, Pageable pageable) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<User> theQuery = null;
	    if (theSearch != null && theSearch.trim().length() > 0) {
	        theQuery = currentSession.createQuery("from User where phoneNumber like :thePhoneNumber or lower(email) like :theEmail", User.class);
	        theQuery.setParameter("thePhoneNumber", "%" + theSearch + "%");
	        theQuery.setParameter("theEmail", "%" + theSearch.toLowerCase() + "%");
	    } else {
	        theQuery = null;
	    }
	    int start = pageable.getPage() * pageable.getSize();
	    theQuery.setFirstResult(start);
	    theQuery.setMaxResults(pageable.getSize());
	    List<User> users = theQuery.list();
	    String countHql = "SELECT COUNT(*) FROM User";
	    Query countQuery = currentSession.createQuery(countHql);
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<User> pageUsers = new Page<>(users, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    return pageUsers;
	}
	
	@Override
	public Page<User> findAll (Pageable pageable) {
		Session session = sessionFactory.openSession();
	    Query<User> query = session.createQuery("FROM User", User.class);
	    int start = pageable.getPage() * pageable.getSize();
	    query.setFirstResult(start);
	    query.setMaxResults(pageable.getSize());
	    List<User> users = query.list();
	    String countHql = "SELECT COUNT(*) FROM User";
	    Query countQuery = session.createQuery(countHql);
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<User> pageUsers = new Page<>(users, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    session.close();
	    return pageUsers;
    }
}
