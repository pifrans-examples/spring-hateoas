package com.pifrans.springhateoas.hateoas;

import com.pifrans.springhateoas.controllers.UserController;
import com.pifrans.springhateoas.domains.dtos.UserDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoas {


    public UserDTO.All addCollection(UserDTO.All object) {
        return object.add(linkTo(methodOn(UserController.class).findAll()).withRel(IanaLinkRelations.COLLECTION));
    }

    public UserDTO.All addSelf(UserDTO.All object) {
        return object.add(linkTo(methodOn(UserController.class).findById(object.getId())).withSelfRel());
    }

    public CollectionModel<UserDTO.All> addSelf(List<UserDTO.All> list) {
        final List<UserDTO.All> listHateoas = list.stream().map(dto -> dto.add(linkTo(methodOn(UserController.class).findById(dto.getId())).withSelfRel())).toList();
        final CollectionModel<UserDTO.All> collectionModel = CollectionModel.of(listHateoas);
        return collectionModel.add(linkTo(methodOn(UserController.class).findAll()).withSelfRel());
    }
}
