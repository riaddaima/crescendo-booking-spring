package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.dtos.User;
import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
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
    @WithMockUser(username = "r.daima@aui.ma")
    public void createUserTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        User user = new User();
        user.setEmail("testuser@test.com");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    @DisplayName("Validate the fields when creating a new user.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void createUserInvalidFieldsTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        User user = new User();
        user.setEmail("testuser");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    @Test
    @DisplayName("Validate if the provided email already exists.")
    @WithMockUser
    public void createUserEmailExists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        User user = new User();
        user.setEmail("r.daima@aui.ma");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    @Test
    @DisplayName("Authenticate a valid user.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void authenticateTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        User user = new User();
        user.setEmail("r.daima@aui.ma");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user/authenticate", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Validate if the provided credentials are wrong.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void authenticateInvalidTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        User user = new User();
        user.setEmail("testuser@test.com");
        user.setPassword("wrong-password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/user/authenticate", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
