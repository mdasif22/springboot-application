package com.mdasif.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdasif.rest.webservices.restfulwebservices.user.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
