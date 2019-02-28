package com.azino.eav.repository;

import com.azino.eav.model.eav.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EntityRepo extends CrudRepository<Entity, Long> {

    Entity findByName(String name);

    Iterable<Entity> findAllByType(String type);

}
