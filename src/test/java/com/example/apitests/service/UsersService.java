package com.example.apitests.service;

import com.example.apitests.model.User;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class UsersService extends BaseApiService {
    private String usersEndpoint = "/users";

    public Response getUsers() {
        return getBaseUrl()
                .when()
                .get(usersEndpoint);
    }
}


