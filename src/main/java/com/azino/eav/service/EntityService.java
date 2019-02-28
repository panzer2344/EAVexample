package com.azino.eav.service;

import com.azino.eav.model.eav.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface EntityService extends BaseService<Entity> {

    List<Entity> findAllByType(String type);

    List<Long> findIdsByType(String type);

}
