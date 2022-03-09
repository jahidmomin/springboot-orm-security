package com.security.springboot.orm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springboot.orm.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser,Integer>{
	Optional<MyUser> findUserByEmail(String email);
}
