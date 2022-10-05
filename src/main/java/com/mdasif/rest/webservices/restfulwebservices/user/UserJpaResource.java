package com.mdasif.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.mdasif.rest.webservices.restfulwebservices.jpa.PostRepo;
import com.mdasif.rest.webservices.restfulwebservices.jpa.UserRepo;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepo userrepo;
	private PostRepo postrepo;

	public UserJpaResource(UserRepo repo,PostRepo postrepo) {
		this.userrepo=repo;
		this.postrepo=postrepo;
	}
	
	// retrieving all users
	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers() {
		return userrepo.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("ID NOT FOUND: ");
		}

		EntityModel<User> resource = EntityModel.of(user.get());
		var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(link.withRel("/jpa/all-users"));

		return resource;
	}

//	@PostMapping(path="/createUsers")
//	public void createUser(@Valid @RequestBody User user) {
//		User savedUser = service.save(user);
//	}

	@PostMapping(path = "/jpa/createUsers")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userrepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void delete(@PathVariable int id) throws UserNotFoundException {
		userrepo .deleteById(id);
		//User user = repo .deleteById(id);
//		if (user == null) {
//			throw new UserNotFoundException("ID NOT FOUND TO DELETE : " + id);
//		}
	}
	
	//Get all posts of a user
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("ID NOT FOUND: ");
		}
		return user.get().getPosts();
		
	}
	
	//Post a post for a user
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("ID NOT FOUND: ");
		}
		post.setUser(user.get());
		Post savedPost = postrepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
		
	}
}
