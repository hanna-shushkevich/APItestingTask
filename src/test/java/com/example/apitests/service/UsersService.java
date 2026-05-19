package com.example.apitests.service;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse; /// unused import

public class UsersService extends BaseApiService {
    /// it's a constant, we don't want to change it from anywhere except here
    private static final String USERS_ENDPOINT = "/users";

    public Response getUsers() {
        return getBaseUrl()
                .when()
                .get(USERS_ENDPOINT);
    }
}


