package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Plan;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/plan")
public class PlanRestController {

    @Autowired
    PlanRepository planRepository;

    @PostMapping
    public boolean createPlan(@RequestBody Plan dto) {
        if (!validateFields(dto))
            return false;
        // Maybe refactor not at this level
        com.crescendo.booking.crescendobookingspring.data.entities.Plan plan =
                new com.crescendo.booking.crescendobookingspring.data.entities.Plan(
                        dto.getTitle(), dto.getType(),dto.getPrice(), dto.getNumKids()
                );
        planRepository.save(plan);
        return true;
    }

    private boolean validateFields(Plan dto) {
        return (dto.getTitle() != null && dto.getType() != null && dto.getPrice() >= 0f && dto.getNumKids() > 0);
    }
}
