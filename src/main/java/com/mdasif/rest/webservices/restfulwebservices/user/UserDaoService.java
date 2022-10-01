package com.mdasif.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;


import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static int usersCount=3; 
	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1,"Asif",new Date()));
		users.add(new User(2,"irfan",new Date()));
		users.add(new User(3,"Javed",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user); 
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			} 
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		//Iterator itr = (Iterator) users.iterator();
		
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId()==id) {
				
				itr.remove();
				return user;
			}
		}
		return null; 
	}

}
