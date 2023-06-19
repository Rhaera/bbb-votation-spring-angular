package com.github.rhaera.bbbvotationapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "votes")
public class Vote {
    @Id
    private String id;
    @Field(value = "_participator")
    private Participator participator;
    @Field(value = "_instant")
    private Instant voteInstant;
}
