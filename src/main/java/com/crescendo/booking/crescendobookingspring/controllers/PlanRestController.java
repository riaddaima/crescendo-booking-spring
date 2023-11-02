package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Plan;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import com.crescendo.booking.crescendobookingspring.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/plan")
public class PlanRestController {

    @Autowired
    PlanService planService;

    @PostMapping
    public boolean createPlan(@RequestBody Plan dto) {
        if (!validateFields(dto))
            return false;
        return planService.createPlan(dto);
    }

    @DeleteMapping
    public boolean deletePlan(@RequestParam Long id) {
        return planService.deletePlan(id);
    }

    @PutMapping
    public boolean updatePlan(@RequestParam Long id, @RequestBody Plan dto) {
        if (!validateFields(dto))
            return false;
        return planService.updatePlan(id, dto);
    }

    @GetMapping
    public List<com.crescendo.booking.crescendobookingspring.data.entities.Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    private boolean validateFields(Plan dto) {
        return (dto.getTitle() != null && dto.getType() != null && dto.getPrice() >= 0f && dto.getNumKids() > 0);
    }
}
