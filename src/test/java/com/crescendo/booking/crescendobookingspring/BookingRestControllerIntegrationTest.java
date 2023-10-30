package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.repositories.BookingRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
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
@DisplayName("Integration testing for Booking Rest Controller")
@Sql(scripts = {"/user.sql", "/event.sql", "/plan.sql"})
public class BookingRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Creates a new booking for a user.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void createBookingTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = this.restTemplate.exchange(
                baseUrl + port + "/rest/booking?event=1&plan=1", HttpMethod.POST, request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }
}
