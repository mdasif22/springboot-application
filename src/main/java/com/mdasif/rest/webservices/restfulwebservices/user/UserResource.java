package com.mdasif.rest.webservices.restfulwebservices.user;

//import javax.validation.Valid;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	// retrieving all users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("ID NOT FOUND: ");
		}

		EntityModel<User> resource = EntityModel.of(user);
		var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(link.withRel("all-users"));

		return resource;
	}

//	@PostMapping(path="/createUsers")
//	public void createUser(@Valid @RequestBody User user) {
//		User savedUser = service.save(user);
//	}

	@PostMapping(path = "/createUsers")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@DeleteMapping(path = "/users/{id}")
	public void delete(@PathVariable int id) throws UserNotFoundException {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("ID NOT FOUND TO DELETE : " + id);
		}
	}
}
