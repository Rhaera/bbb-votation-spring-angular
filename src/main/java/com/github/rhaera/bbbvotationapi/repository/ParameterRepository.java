package com.github.rhaera.bbbvotationapi.repository;

import com.github.rhaera.bbbvotationapi.model.Parameter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParameterRepository extends MongoRepository<Parameter, String> {

}
