package com.github.rhaera.bbbvotationapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parameters")
public class Parameter {

    @Id
    @Getter
    private String key;

    @Field(value = "_value")
    private String value;
}
