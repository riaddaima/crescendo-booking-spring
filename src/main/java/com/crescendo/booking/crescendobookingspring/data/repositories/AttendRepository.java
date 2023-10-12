package com.crescendo.booking.crescendobookingspring.data.repositories;

import com.crescendo.booking.crescendobookingspring.data.entities.Attend;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AttendRepository extends BaseRepository<Attend> {
}
