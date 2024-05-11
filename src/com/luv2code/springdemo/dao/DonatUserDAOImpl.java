package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.DonatUser;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Repository
public class DonatUserDAOImpl implements DonatUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveDonatForUser(DonatUser theDonatUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(theDonatUser);
	}

	@Override
	public List<DonatUser> getUserByDonatId(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<DonatUser> theQuery = currentSession.createQuery("from DonatUser where donat_id = :theId", DonatUser.class );
        theQuery.setParameter("theId", theId);
		List<DonatUser> donatUsers = theQuery.getResultList();
		return donatUsers;
	}

//	sum money in a donation
	@Override
	public Double sumMoney(int theId) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Double> theQuery = currentSession.createQuery("select sum(money) from DonatUser where donat_id = :theId", Double.class);
	    theQuery.setParameter("theId", theId);
	    return theQuery.uniqueResult() != null ? theQuery.uniqueResult() : 0.0;
	}
	
//	sum money by a user
	@Override
	public Double sumMoneyByUser(int theId) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Double> theQuery = currentSession.createQuery("select sum(money) from DonatUser where user_id = :theId", Double.class);
	    theQuery.setParameter("theId", theId);
	    return theQuery.uniqueResult() != null ? theQuery.uniqueResult() : 0.0;
	}
	
	@Override
	public Page<DonatUser> getUserByDonatId(int theId, Pageable pageable) {
		Session currentSession = sessionFactory.getCurrentSession();
	    Query<DonatUser> theQuery = currentSession.createQuery("from DonatUser where donat_id = :theId", DonatUser.class );
        theQuery.setParameter("theId", theId);
	    int start = pageable.getPage() * pageable.getSize();
	    theQuery.setFirstResult(start);
	    theQuery.setMaxResults(pageable.getSize());
	    List<DonatUser> donatUsers = theQuery.list();
	    String countHql = "SELECT COUNT(*) FROM DonatUser where donat_id = :theId";
	    Query countQuery = currentSession.createQuery(countHql);
	    countQuery.setParameter("theId", theId);
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<DonatUser> pageDonatUsers = new Page<>(donatUsers, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    return pageDonatUsers;
	}
	@Override
	public Page<DonatUser> findAll(int theId, Pageable pageable) {
		Session session = sessionFactory.openSession();
	    Query<DonatUser> query = session.createQuery("FROM DonatUser where donat_id = :theId", DonatUser.class);
	    query.setParameter("theId", theId);
	    int start = pageable.getPage() * pageable.getSize();
	    query.setFirstResult(start);
	    query.setMaxResults(pageable.getSize());
	    List<DonatUser> donatUsers = query.list();
	    String countHql = "SELECT COUNT(*) FROM DonatUser where donat_id = :theId";
	    Query countQuery = session.createQuery(countHql);
	    countQuery.setParameter("theId", theId);
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<DonatUser> pageDonatUsers = new Page<>(donatUsers, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    session.close();
	    return pageDonatUsers;
    }

	@Override
	public List<DonatUser> getDonatByUserId(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<DonatUser> theQuery = currentSession.createQuery("from DonatUser where user_id = :theId", DonatUser.class );
        theQuery.setParameter("theId", theId);
		List<DonatUser> donatUsers = theQuery.getResultList();
		return donatUsers;
	}

	@Override
	public Page<DonatUser> searchUserDonated(int theId, String theSearch, Pageable pageable) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<DonatUser> theQuery = null;
	    if (theSearch != null && theSearch.trim().length() > 0) {
	        theQuery = currentSession.createQuery("from DonatUser where donat_id = :theId and lower(name) like :theName", DonatUser.class);
		    theQuery.setParameter("theId", theId);
	        theQuery.setParameter("theName", "%" + theSearch.toLowerCase() + "%");
	    } else {
	        theQuery = null;
	    }
	    int start = pageable.getPage() * pageable.getSize();
	    theQuery.setFirstResult(start);
	    theQuery.setMaxResults(pageable.getSize());
	    List<DonatUser> donatUsers = theQuery.getResultList();
	    String countHql = "SELECT COUNT(*) FROM DonatUser where donat_id = :theId and lower(name) like :theName";
	    Query countQuery = currentSession.createQuery(countHql);
	    countQuery.setParameter("theId", theId);
	    countQuery.setParameter("theName", "%" + theSearch.toLowerCase() + "%");
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<DonatUser> pageSearchUserDonated = new Page<>(donatUsers, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    return pageSearchUserDonated;
	}

	@Override
	public Page<DonatUser> findAll(int theId, String theSearch, Pageable pageable) {
		Session session = sessionFactory.openSession();
	    Query<DonatUser> query = session.createQuery("FROM DonatUser where donat_id = :theId and lower(name) like :theName", DonatUser.class);
	    query.setParameter("theId", theId);
	    query.setParameter("theName", "%" + theSearch.toLowerCase() + "%");
	    int start = pageable.getPage() * pageable.getSize();
	    query.setFirstResult(start);
	    query.setMaxResults(pageable.getSize());
	    List<DonatUser> donatUsers = query.list();
	    String countHql = "SELECT COUNT(*) FROM DonatUser where donat_id = :theId and lower(name) like :theName";
	    Query countQuery = session.createQuery(countHql);
	    countQuery.setParameter("theId", theId);
	    countQuery.setParameter("theName", "%" + theSearch.toLowerCase() + "%");
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<DonatUser> pageSearchUserDonated = new Page<>(donatUsers, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    session.close();
	    return pageSearchUserDonated;
	}
	
}
