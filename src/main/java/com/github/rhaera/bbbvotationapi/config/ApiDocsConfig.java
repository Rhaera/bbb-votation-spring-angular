package com.github.rhaera.bbbvotationapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger/api/docs")
@OpenAPIDefinition(info =
@Info(
        title   = "BBB Voting API",
        version = "1.0.0",
        description = "API for simulate BBB voting process"
))
public class ApiDocsConfig {

}
