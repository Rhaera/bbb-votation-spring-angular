package com.github.rhaera.bbbvotationapi.controller;

import com.github.rhaera.bbbvotationapi.model.Participator;
import com.github.rhaera.bbbvotationapi.repository.ParticipatorRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/api/participants")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j(topic = "info")
public class ParticipatorController {
    private static final Pattern namePattern = Pattern.compile("([0-9])");

    private final ParticipatorRepository repository;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestParam(value = "name") String name) {
        if (namePattern.matcher(name)
                        .find())
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .build();
        return repository.findAll()
                        .stream()
                        .map(Participator::getName)
                        .anyMatch(n -> n.equals(name)) ?
                        ResponseEntity.ok(name) :
                        ResponseEntity.created(UriComponentsBuilder.fromPath("/api/participator/saved/")
                                                                    .build(name))
                                                                    .body(repository.save(new Participator(
                                                                            Arrays.toString(UUID.randomUUID()
                                                                                                .toString()
                                                                                                .split("-"))
                                                                                                .concat(UUID.randomUUID()
                                                                                                .toString()
                                                                                                .split("-")[0]),
                                                                            name
                                                                    )).getName());
    }

    @GetMapping(value = "/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Participator> read(@PathVariable(value = "name") String n) {
        return repository.findAll()
                        .stream()
                        .map(Participator::getName)
                        .anyMatch(name -> name.equals(n)) ?
                        ResponseEntity.ok(repository.findAll()
                                                    .stream()
                                                    .filter(participator -> Objects.equals(participator.getName(), n))
                                                    .findFirst()
                                                    .orElse(new Participator())) :
                        ResponseEntity.notFound()
                                        .build();
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CollectionModel<Participator> readAll() {
        return CollectionModel.of(repository.findAll());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CollectionModel<Participator> deleteAll() {
        CollectionModel<Participator> deletedList = CollectionModel.of(repository.findAll());
        repository.deleteAll();
        return deletedList;
    }
}
