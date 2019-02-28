package com.azino.eav.service.impl;

import com.azino.eav.model.eav.Attribute;
import com.azino.eav.repository.AttributeRepo;
import com.azino.eav.service.AttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttributeServiceImpl extends BaseServiceImpl<Attribute, AttributeRepo> implements AttributeService {

    public AttributeServiceImpl(AttributeRepo attributeRepo){
        super(attributeRepo);
    }

    @Override
    public void updateDecorator(Attribute entityDb, Attribute entity) {
        entityDb.setName(entity.getName());
    }

    @Override
    public Attribute findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Attribute> getAllAttributesByEntityId(Long id) {
        List<Attribute> attributes = new ArrayList<>();
        repository.getAllAttributesByEntityId(id).forEach(attributes::add);
        return attributes;
    }
}
