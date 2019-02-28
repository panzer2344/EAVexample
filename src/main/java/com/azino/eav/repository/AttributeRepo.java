package com.azino.eav.repository;

import com.azino.eav.model.eav.Attribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AttributeRepo extends CrudRepository<Attribute, Long> {

    Attribute findByName(String name);

    @Query(nativeQuery = true,
            value = "select a.attribute_id, a.name" +
                    " from attribute_values as av join attribute as a" +
                    " on a.attribute_id = av.attribute_id" +
                    "  where a.entity_id = :id")
    Iterable<Attribute> getAllAttributesByEntityId(Long id);
}
