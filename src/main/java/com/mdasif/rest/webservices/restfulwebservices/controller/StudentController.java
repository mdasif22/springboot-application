package com.mdasif.rest.webservices.restfulwebservices.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/request")
public class StudentController {
	
	//@GetMapping("/getName")
	@GetMapping(path = "/hello")
	public String helloWorld() {
		
		return "Hello i am Asif";
	}

	@GetMapping(path = "/Bean")
	public helloBean bean() {
		
		return new helloBean("Hello Java");
	}
	
	@GetMapping(path = "/path-variable/{name}")
	public helloBean bean(@PathVariable String name) {
		
		return new helloBean(String.format("Hello Spring boot, %s ",name));
	}
}
