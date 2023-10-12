package com.crescendo.booking.crescendobookingspring.data.repositories;

import com.crescendo.booking.crescendobookingspring.data.entities.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends BaseRepository<User> {
}
