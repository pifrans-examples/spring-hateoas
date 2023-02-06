package com.pifrans.springhateoas.controllers;


import com.pifrans.springhateoas.domains.dtos.UserDTO;
import com.pifrans.springhateoas.hateoas.UserHateoas;
import com.pifrans.springhateoas.services.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserHateoas userHateoas;


    public UserController(UserService userService, UserHateoas userHateoas) {
        this.userService = userService;
        this.userHateoas = userHateoas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.All> findById(@PathVariable Long id) {
        final UserDTO.All object = userService.findById(id);
        final UserDTO.All objectHateoas = userHateoas.addCollection(object);
        return new ResponseEntity<>(objectHateoas, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserDTO.All>> findAll() {
        final List<UserDTO.All> list = userService.findAll();
        final CollectionModel<UserDTO.All> collectionModel = userHateoas.addSelf(list);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO.All> save(@Validated @RequestBody UserDTO.Save body) {
        final UserDTO.All object = userService.save(body);
        final UserDTO.All objectHateos = userHateoas.addSelf(object);
        return new ResponseEntity<>(objectHateos, HttpStatus.CREATED);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<CollectionModel<UserDTO.All>> saveAll(@Validated @RequestBody List<UserDTO.Save> body) {
        final List<UserDTO.All> list = userService.saveAll(body);
        final CollectionModel<UserDTO.All> collectionModel = userHateoas.addSelf(list);
        return new ResponseEntity<>(collectionModel, HttpStatus.CREATED);
    }
}
