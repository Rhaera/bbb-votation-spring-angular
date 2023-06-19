package com.github.rhaera.bbbvotationapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StatusController {

    @GetMapping(value = "/status")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("online");
    }
}
