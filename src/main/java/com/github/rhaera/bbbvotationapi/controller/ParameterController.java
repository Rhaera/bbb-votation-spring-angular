package com.github.rhaera.bbbvotationapi.controller;

import com.github.rhaera.bbbvotationapi.model.Parameter;
import com.github.rhaera.bbbvotationapi.repository.ParameterRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/parameters")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j(topic = "info")
public class ParameterController {
    private final ParameterRepository repository;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Parameter> create(@RequestBody Parameter parameter) {
        return repository.findById(parameter.getKey())
                        .isPresent() ?
                        ResponseEntity.ok(repository.findById(parameter.getKey())
                                                    .get()) :
                        ResponseEntity.created(UriComponentsBuilder.fromPath("/api/parameters/saved/")
                                                                    .build(parameter.getKey()))
                                                                    .body(repository.save(parameter));
    }

    @GetMapping(value = "/properties")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Parameter> read(@RequestParam String key) {
        return repository.findById(key)
                        .isPresent() ?
                        ResponseEntity.ok(repository.findById(key)
                                                    .get()) :
                        ResponseEntity.notFound()
                                        .build();
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CollectionModel<Parameter> readAll() {
        return CollectionModel.of(repository.findAll());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CollectionModel<Parameter> deleteAll() {
        CollectionModel<Parameter> deletedList = CollectionModel.of(repository.findAll());
        repository.deleteAll();
        return deletedList;
    }
}
