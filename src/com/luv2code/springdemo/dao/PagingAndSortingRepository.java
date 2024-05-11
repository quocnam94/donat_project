package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.page.Page;
import com.luv2code.springdemo.page.Pageable;

public interface PagingAndSortingRepository<T1, T2> {

	Page<User> findAll(Pageable pageable);

}
