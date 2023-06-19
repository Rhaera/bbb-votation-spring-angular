package com.github.rhaera.bbbvotationapi.repository;

import com.github.rhaera.bbbvotationapi.model.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingRepository extends MongoRepository<Vote, String> {

}
