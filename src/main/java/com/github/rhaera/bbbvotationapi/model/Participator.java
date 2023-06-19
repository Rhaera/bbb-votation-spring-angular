package com.github.rhaera.bbbvotationapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "participants")
public class Participator implements Serializable {
    @Id
    private String id;
    @Field(value = "_name")
    @Getter
    private String name;
}
