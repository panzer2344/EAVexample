package com.azino.eav.service.impl;

import com.azino.eav.model.eav.Entity;
import com.azino.eav.model.eav.Value;
import com.azino.eav.repository.EntityRepo;
import com.azino.eav.service.EntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntityServiceImpl extends BaseServiceImpl<Entity, EntityRepo> implements EntityService {

    public EntityServiceImpl(EntityRepo entityRepo){
        super(entityRepo);
    }

    @Override
    public void updateDecorator(Entity entityDb, Entity entity) {
        entityDb.setName(entity.getName());
    }

    @Override
    public List<Entity> findAllByType(String type) {
        List<Entity> values = new ArrayList<>();
        repository.findAllByType(type).forEach(values::add);
        return values;
    }

    @Override
    public List<Long> findIdsByType(String type) {
        List<Long> ids = new ArrayList<>();
        repository.findAllByType(type).forEach(entity -> ids.add(entity.getId()));
        return ids;
    }

    /*@Override
    @Transactional
    public Entity update(Long id, Entity entity) {
        Optional<Entity> entityOptional = repository.findById(id);
        Entity entityDb = null;
        if(entityOptional.isPresent()){
            entityDb = entityOptional.get();
            entityDb.setName(entity.getName());
        }
        return entityDb;
    }*/
}
