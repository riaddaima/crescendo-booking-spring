package com.crescendo.booking.crescendobookingspring;

import com.crescendo.booking.crescendobookingspring.security.UserAuditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.crescendo.booking.crescendobookingspring.data.entities.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@EnableCaching
public class CrescendoBookingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrescendoBookingSpringApplication.class, args);
    }

    @Bean
    AuditorAware<User> auditorProvider() {
        return new UserAuditor();
    }

}
