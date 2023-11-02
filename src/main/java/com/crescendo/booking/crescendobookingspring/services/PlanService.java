package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Plan;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;

    public boolean createPlan(com.crescendo.booking.crescendobookingspring.data.dtos.Plan dto) {
        com.crescendo.booking.crescendobookingspring.data.entities.Plan plan =
                new com.crescendo.booking.crescendobookingspring.data.entities.Plan(
                        dto.getTitle(), dto.getType(),dto.getPrice(), dto.getNumKids()
                );
        planRepository.save(plan);
        return true;
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
}
