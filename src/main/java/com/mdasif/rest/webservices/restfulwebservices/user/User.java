package com.mdasif.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User {
	private Integer id;
	@Size(min = 2,message="Name should have 2 character!!")
	@JsonProperty("user_name")
	private String name;
	@Past
	private Date birthdate;
	
	public User() {
		
	}
	
	public User(Integer id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	
	public Integer getId() {
		return id; 
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return "User [id = " + id + ", \n name = " + name + ", \n birthdate = " + birthdate + "]";
	} 
}

