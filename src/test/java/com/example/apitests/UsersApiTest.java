package com.example.apitests;

import com.example.apitests.service.UsersService;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.describedAs;

public class UsersApiTest {

    @Test
    @DisplayName("1. GET /users returns status code 200 OK")
    public void testGetUsersStatusCode() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();

        response.then().assertThat()
                .statusCode(describedAs("Step 1 Failed: GET /users should return status code 200 OK", equalTo(200)));
    }

    @Test
    @DisplayName("2. GET /users returns content-type header as application/json; charset=utf-8")
    public void testGetUsersResponseHeader() {

        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        response.then().assertThat()
                .header("content-type", describedAs("Step 2 Failed: Response header content-type should be application/json; charset=utf-8", equalTo("application/json; charset=utf-8")));
    }

    @Test
    @DisplayName("3. GET /users returns array of 10 users in response body")
    public void testGetUsersResponseBody() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        response.then().assertThat()
                .body("", describedAs("Step 3 Failed: Response body should contain array of 10 users", hasSize(10)));
    }
}

