# API Testing Task

Minimal Maven-based API tests for `https://jsonplaceholder.typicode.com/users` using JUnit Jupiter and Rest Assured.

## Run tests

```zsh
mvn test
```

## Structure

- `src/test/java/com/example/apitests/service/BaseApiService.java` - shared GET request setup
- `src/test/java/com/example/apitests/service/UsersService.java` - service object for `/users`
- `src/test/java/com/example/apitests/UsersApiTest.java` - three readable API tests
- `src/test/resources/junit-platform.properties` - enables parallel test execution

