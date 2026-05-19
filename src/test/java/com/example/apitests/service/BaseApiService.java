package com.example.apitests.service;

import io.restassured.response.ValidatableResponse; /// unused import
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApiService {
    /// it's a constant, we don't want to change it from anywhere except here
    /// COMMON_NAMING_FOR_CONSTANTS <-- should be like this
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    protected RequestSpecification getBaseUrl() {
        return given()
                .baseUri(BASE_URL);
    }
}


