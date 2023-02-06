package com.pifrans.springhateoas.controllers;

import com.pifrans.springhateoas.domains.dtos.RootEntryPointDTO;
import com.pifrans.springhateoas.hateoas.RootEntryPointHateoas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootEntryPointController {
    private final RootEntryPointHateoas rootEntryPointHateoas;


    public RootEntryPointController(RootEntryPointHateoas rootEntryPointHateoas) {
        this.rootEntryPointHateoas = rootEntryPointHateoas;
    }

    @GetMapping
    public ResponseEntity<RootEntryPointDTO> root() {
        final RootEntryPointDTO objectHateoas = rootEntryPointHateoas.add();
        return new ResponseEntity<>(objectHateoas, HttpStatus.OK);
    }
}
