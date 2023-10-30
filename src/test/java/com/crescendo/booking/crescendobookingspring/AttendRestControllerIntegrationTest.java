package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.repositories.BookingRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration testing for Attend Rest Controller")
@Sql(scripts = {"/user.sql", "/event.sql", "/plan.sql", "/booking.sql", "/dependent.sql"})
public class AttendRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Creates a new attend for a user.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void createAttendTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<List<Long>> request = new HttpEntity<>(new ArrayList<>(List.of(1L)), headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(
                baseUrl + port + "/rest/attend?booking=1", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }
}
