package com.example.apitests.service;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApiService {
    private static final String DEV_URL = "https://jsonplaceholder.typicode.com";
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com";
    
    protected String baseUrl;

    public BaseApiService() {
        String environment = System.getProperty("environment", "dev");
        this.baseUrl = getUrlByEnvironment(environment);
    }

    private String getUrlByEnvironment(String environment) {
        return switch (environment.toLowerCase()) {
            case "test" -> TEST_URL;
            case "dev" -> DEV_URL;
            default -> throw new IllegalArgumentException("Unknown environment: " + environment + ". Supported: dev, test");
        };
    }

    protected RequestSpecification getBaseUrl() {
        return given()
                .baseUri(baseUrl);
    }
}


