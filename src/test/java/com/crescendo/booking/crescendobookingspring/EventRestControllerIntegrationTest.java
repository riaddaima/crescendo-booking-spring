package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.data.dtos.Event;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration testing for Dependent Rest Controller")
public class EventRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName("Creates a new event")
    @WithMockUser(username = "r.daima@aui.ma")
    public void createEventTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        Event event = new Event("Big Kids", "1698140828", "1698145715", "1698137288",
                12, 2, 4, "Crescendo Home - 2, Rue Hamza, Haut Agdal, Rabat",
                "Lorem Ipsum Dolor");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Event> request = new HttpEntity<>(event, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(
                baseUrl + port + "/rest/event", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();

        com.crescendo.booking.crescendobookingspring.data.entities.Event createdEvent =
                eventRepository.findByName(event.getName());
        assertThat(createdEvent.getName()).isEqualTo(event.getName());
    }

    @Test
    @DisplayName("Validate the fields when creating a new event.")
    @WithMockUser(username = "r.daima@aui.ma")
    public void createEventInvalidFieldsTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        Event event = new Event("Big Kids", "1698145715", "1698140828", "1698137288",
                12, -1, 4, "Crescendo Home - 2, Rue Hamza, Haut Agdal, Rabat",
                "Lorem Ipsum Dolor");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Event> request = new HttpEntity<>(event, headers);

        ResponseEntity<Boolean> response = this.restTemplate.postForEntity(
                baseUrl + port + "/rest/event", request, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }
}
