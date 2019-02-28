package com.azino.eav.repository;

import com.azino.eav.model.eav.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ValueRepo extends CrudRepository<Value, Long> {

    void deleteAllByEntity_Id(Long id);

    void deleteAllByEntity_Name(String name);

    Iterable<Value> findAllByEntity_Id(Long id);

    Iterable<Value> findAllByEntity_Name(String name);

}
