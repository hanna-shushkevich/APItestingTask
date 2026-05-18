package com.example.apitests;

import com.example.apitests.model.User;
import com.example.apitests.service.UsersService;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class UsersApiTest {

    @Test
    @DisplayName("1. GET /users returns status code 200 OK")
    public void testGetUsersStatusCode() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();

        response.then().assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("2. GET /users returns content-type header as application/json; charset=utf-8")
    public void testGetUsersResponseHeader() {

        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        response.then().assertThat()
                .header("content-type", equalTo("application/json; charset=utf-8"));
    }

    @Test
    @DisplayName("3. GET /users returns array of 10 users in response body")
    public void testGetUsersResponseBody() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        response.then().assertThat()
                .body("", hasSize(10));
    }
}

