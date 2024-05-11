package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

@Repository
public class DonatDAOImpl implements DonatDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveDonat(Donat theDonat) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theDonat);
	}

	@Override
	public Donat getDonat(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Donat theDonat = currentSession.get(Donat.class, theId);
		return theDonat;
	}

	@Override
	public void deleteDonat(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Donat> theQuery = currentSession.createQuery("update Donat set isActive=false where id=:donatId", Donat.class);
		theQuery.setParameter("donatId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public String changeStatusDonat(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Donat theDonat = currentSession.get(Donat.class, theId);
		if (theDonat.getStatus().equals("Created")) {
			return theDonat.setStatus("Processing");
		} 
		if (theDonat.getStatus().equals("Processing")) {
			return theDonat.setStatus("Ended");
		} 
		if (theDonat.getStatus().equals("Ended")) {
			return theDonat.setStatus("Closed");
		} 
		else {
			return theDonat.setStatus("Created");
		}
	}
	
	@Override
	public Page<Donat> searchDonats(String theSearch, Pageable pageable) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Donat> theQuery = null;
	    String hql = "FROM Donat WHERE isActive = 1 AND "
	            + "(phoneNumber LIKE :thePhoneNumber "
	            + "OR LOWER(code) LIKE :theCode "
	            + "OR LOWER(organization) LIKE :theOrganization "
	            + "OR LOWER(status) LIKE :theStatus)";
	    if (theSearch != null && theSearch.trim().length() > 0) {
	        theQuery = currentSession.createQuery(hql, Donat.class);
	        theQuery.setParameter("thePhoneNumber", "%" + theSearch + "%");
	        theQuery.setParameter("theCode", "%" + theSearch.toLowerCase() + "%");
	        theQuery.setParameter("theOrganization", "%" + theSearch.toLowerCase() + "%");
	        theQuery.setParameter("theStatus", "%" + theSearch.toLowerCase() + "%");
	    } else {
	        theQuery = currentSession.createQuery("FROM Donat WHERE isActive = 1", Donat.class);
	    }
	    int start = pageable.getPage() * pageable.getSize();
	    theQuery.setFirstResult(start);
	    theQuery.setMaxResults(pageable.getSize());
	    List<Donat> donats = theQuery.list();
	    Query countQuery = currentSession.createQuery("SELECT COUNT(*) FROM Donat WHERE isActive = 1");
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<Donat> pageDonats = new Page<>(donats, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    return pageDonats;
	}
	
	@Override
	public Page<Donat> findAll (Pageable pageable) {
		Session session = sessionFactory.openSession();
	    Query<Donat> query = session.createQuery("FROM Donat WHERE isActive = 1", Donat.class);
	    int start = pageable.getPage() * pageable.getSize();
	    query.setFirstResult(start);
	    query.setMaxResults(pageable.getSize());
	    List<Donat> donats = query.list();
	    Query countQuery = session.createQuery("SELECT COUNT(*) FROM Donat WHERE isActive = 1");
	    Long totalElements = (Long) countQuery.uniqueResult();
	    int totalPages = (int) Math.ceil((double) totalElements / pageable.getSize());
	    Page<Donat> pageUser = new Page<>(donats, totalElements, totalPages, pageable.getPage(), pageable.getSize());
	    session.close();
	    return pageUser;
    }
}
