package com.security.springboot.orm.service;

import org.springframework.stereotype.Service;

import com.security.springboot.orm.model.MyUser;


public interface IUserService {
	public Integer saveUser(MyUser user);
}
