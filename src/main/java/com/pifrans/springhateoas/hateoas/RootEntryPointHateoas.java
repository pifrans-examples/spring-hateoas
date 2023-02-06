package com.pifrans.springhateoas.hateoas;

import com.pifrans.springhateoas.controllers.UserController;
import com.pifrans.springhateoas.domains.dtos.RootEntryPointDTO;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RootEntryPointHateoas {


    public RootEntryPointDTO add() {
        return new RootEntryPointDTO().add(linkTo(methodOn(UserController.class).findAll()).withRel("users"));
    }
}
