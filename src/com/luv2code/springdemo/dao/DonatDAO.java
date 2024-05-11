package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Donat;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

public interface DonatDAO {

	public void saveDonat(Donat theDonat);

	public Donat getDonat(int theId);

	public void deleteDonat(int theId);

	public String changeStatusDonat(int theId);

	public Page<Donat> findAll(Pageable pageable);

	public Page<Donat> searchDonats(String theSearch, Pageable pageable);

}
