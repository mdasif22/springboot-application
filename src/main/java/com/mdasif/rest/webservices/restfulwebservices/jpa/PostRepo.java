package com.mdasif.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdasif.rest.webservices.restfulwebservices.user.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
