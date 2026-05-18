package com.example.apitests.service;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApiService {
    protected String baseUrl = "https://jsonplaceholder.typicode.com";

    protected RequestSpecification getBaseUrl() {
        return given()
                .baseUri(baseUrl);
    }
}


