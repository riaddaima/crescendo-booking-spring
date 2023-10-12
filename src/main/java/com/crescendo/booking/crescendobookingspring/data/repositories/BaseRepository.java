package com.crescendo.booking.crescendobookingspring.data.repositories;

import com.crescendo.booking.crescendobookingspring.data.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends CrudRepository<E, Long> {
}
