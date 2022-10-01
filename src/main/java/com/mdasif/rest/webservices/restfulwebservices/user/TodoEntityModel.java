package com.mdasif.rest.webservices.restfulwebservices.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class TodoEntityModel extends EntityModel<User> {

    public TodoEntityModel(User content, Iterable<Link> links) {
        super(content, links);
    }

    public TodoEntityModel(User content) {
        super(content);
    }

}