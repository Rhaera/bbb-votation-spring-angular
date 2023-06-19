package com.github.rhaera.bbbvotationapi.controller;

import com.github.rhaera.bbbvotationapi.model.Participator;
import com.github.rhaera.bbbvotationapi.repository.ParticipatorRepository;
import com.github.rhaera.bbbvotationapi.service.VotingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/votes")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j(topic = "info")
public class VotingController {

    private final VotingService service;

    private final ParticipatorRepository repository;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> vote(@RequestParam String name) {
        if (repository.findAll()
                        .stream()
                        .noneMatch(participator -> Objects.equals(participator.getName(), name)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name.concat(" does not exists!"));
        service.sendEvent(repository.findAll()
                                    .stream()
                                    .filter(participator -> Objects.equals(participator.getName(), name))
                                    .findFirst()
                                    .orElse(new Participator()));
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/votes/voted/")
                                                            .build(name))
                                                            .body(("Voted for ").concat(name.concat("!")));
    }
}
