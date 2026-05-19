package com.example.apitests;

import com.example.apitests.service.UsersService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.describedAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiTest {
    /// we do not keep any state in service, which may be modified during test run
    /// UsersService contains methods only => tests will be executed in parallel with no issue
    /// So init 1 instance only => compact code easy to read
    private final UsersService usersService = new UsersService();

    @Test
    @DisplayName("1. GET /users returns status code 200 OK")
    public void testGetUsersStatusCode() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();

        /// this comment for all describedAs(description...)
        /// it's better put to description actual values for meaningful test fail:
        response.then().assertThat()
                .statusCode(describedAs("Step 1 Failed: GET /users should return status code 200 OK", equalTo(200)));

        /// using Junit assertion to pass actual result to fail:
        int actualStatusCode = response.getStatusCode();
        assertEquals(
                200,
                actualStatusCode,
                String.format("Step 1 Failed: GET /users should return status code 200 OK, but got %d", actualStatusCode)
        );
    }

    @Test
    @DisplayName("2. GET /users returns content-type header as application/json; charset=utf-8")
    public void testGetUsersResponseHeader() {

        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        /// added the 2nd validation as asked in task to show both in one test;
        /// import static org.hamcrest.Matchers.notNullValue; -- was used now.
        /// I guess it was done so before, then existence was checked by value verification
        response.then().assertThat()
                .header("content-type", describedAs("Step 2.1 Failed: Response content-type header does not exist", notNullValue()))
                .header("content-type", describedAs("Step 2.2 Failed: Response header content-type should be application/json; charset=utf-8", equalTo("application/json; charset=utf-8")));
    }

    @Test
    @DisplayName("3. GET /users returns array of 10 users in response body")
    public void testGetUsersResponseBody() {
        UsersService usersService = new UsersService();
        Response response = usersService.getUsers();
        response.then().assertThat()
                .body("$", describedAs("Step 3 Failed: Response body should contain array of 10 users", hasSize(10)));
    }
    /// use .body("",...) => .body("$",...) as "$" is conventional JSONPath root symbol
    /// what GPT says:
    /*
    In REST-assured, the path "$" refers to the root of the JSON response, which is standard for asserting properties of the entire response body when it is a JSON array.
    The path "" (empty string) is not officially documented and may not work consistently across different REST-assured versions or may lead to ambiguous behavior.
    Using "$" makes your intent clear: you are asserting on the root element of the response, which is expected to be an array.
    */
}

