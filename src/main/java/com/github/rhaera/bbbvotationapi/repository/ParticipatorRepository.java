package com.github.rhaera.bbbvotationapi.repository;

import com.github.rhaera.bbbvotationapi.model.Participator;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipatorRepository extends MongoRepository<Participator, String> {

}
