package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.dtos.Profile;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration testing for Profile Rest Controller")
@Sql(scripts = {"/user.sql"})
public class ProfileRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "r.daima@aui.ma")
    @DisplayName("Creates a new profile for a user.")
    public void createOwnProfileTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);
        Profile profile = new Profile("Riad", "Daima", "0693502372", true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Profile> request = new HttpEntity<>(profile, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/profile", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();

        User user = userRepository.findByEmail("r.daima@aui.ma");
        assertThat(user.getProfile().getFirstName()).isEqualTo(profile.getFirstName());
        assertThat(user.getProfile().getCreatedBy().getId()).isEqualTo(user.getId());
    }

//    @Test
//    @WithMockUser(username = "r.daima@aui.ma")
//    @DisplayName("Updates a profile for a user.")
//    @Sql(scripts = {"/profile.sql"})
//    public void updateOwnProfileTest() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String token = JwtHelper.generateToken(authentication);
//        Profile profile = new Profile("Samir", "Daima", "0693502372", false);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + token);
//
//        HttpEntity<Profile> request = new HttpEntity<>(profile, headers);
//
//
//        ResponseEntity<Boolean> response = this.restTemplate.exchange(baseUrl + port + "/rest/profile", HttpMethod.PATCH, request, Boolean.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isTrue();
//
//        User user = userRepository.findByEmail("r.daima@aui.ma");
//        assertThat(user.getProfile().getFirstName()).isEqualTo(profile.getFirstName());
//        assertThat(user.getProfile().getIsSubbed()).isEqualTo(profile.getIsSubbed());
//    }

    @Test
    @WithMockUser(username = "r.daima@aui.ma")
    @DisplayName("Validate the fields when creating a new profile.")
    public void createOwnProfileInvalidFieldsTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);
        Profile profile = new Profile(null, "Daima", "0693502372", null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Profile> request = new HttpEntity<>(profile, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(baseUrl + port + "/rest/profile", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

}
