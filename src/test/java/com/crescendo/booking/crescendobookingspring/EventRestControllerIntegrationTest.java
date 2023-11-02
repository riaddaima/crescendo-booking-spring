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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration testing for Event Rest Controller")
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
    @WithMockUser(username = "r.daima@aui.ma", authorities = {"MANAGER"})
    public void createEventTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        Event event = new Event("Big Kids", "1698140828", "1698145715", "1698137288",
                12, 2, 4, "Crescendo Home - 2, Rue Hamza, Haut Agdal, Rabat",
                "Lorem Ipsum Dolor", new ArrayList<>(List.of("r.daima@aui.ma")));


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
    @DisplayName("Retrieve the list of events")
    @WithMockUser(username = "r.daima@aui.ma", authorities = {"MANAGER"})
    @Sql(scripts = {"/user.sql", "/event.sql"})
    public void getEventsTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<Event>> response = this.restTemplate.exchange(
                baseUrl + port + "/rest/event/all", HttpMethod.GET, request, new ParameterizedTypeReference<List<Event>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Event> events = response.getBody();
        assertThat(events).isNotNull();
    }

    @Test
    @DisplayName("Validate the fields when creating a new event.")
    @WithMockUser(username = "r.daima@aui.ma", authorities = {"MANAGER"})
    public void createEventInvalidFieldsTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = JwtHelper.generateToken(authentication);

        Event event = new Event("Big Kids", "1698145715", "1698140828", "1698137288",
                12, -1, 4, "Crescendo Home - 2, Rue Hamza, Haut Agdal, Rabat",
                "Lorem Ipsum Dolor", new ArrayList<>(List.of("r.daima@aui.ma")));


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
