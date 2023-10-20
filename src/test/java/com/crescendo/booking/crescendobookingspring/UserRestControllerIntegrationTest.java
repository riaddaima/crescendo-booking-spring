package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.dtos.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration testing for User Rest Controller")
@Sql(scripts = {"/user.sql"})
public class UserRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "http://localhost:";

    @Test
    @DisplayName("Creates a new test customer user.")
    public void createUserTest() {
        User user = new User();
        user.setEmail("testuser@test.com");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    @DisplayName("Validate the fields when creating a new user.")
    public void createUserInvalidFieldsTest() {
        User user = new User();
        user.setEmail("testuser");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    @Test
    @DisplayName("Validate if the provided email already exists.")
    public void createUserEmailExists() {
        User user = new User();
        user.setEmail("r.daima@aui.ma");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    @Test
    @DisplayName("Authenticate a valid user.")
    public void authenticateTest() {
        User user = new User();
        user.setEmail("testuser@test.com");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user/authenticate", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Validate if the provided credentials are wrong.")
    public void authenticateInvalidTest() {
        User user = new User();
        user.setEmail("testuser@test.com");
        user.setPassword("wrong-password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user/authenticate", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
