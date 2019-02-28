package com.azino.eav.service.impl;

import com.azino.eav.model.eav.Value;
import com.azino.eav.repository.ValueRepo;
import com.azino.eav.service.ValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ValueServiceImpl extends BaseServiceImpl<Value, ValueRepo> implements ValueService {

    public ValueServiceImpl(ValueRepo valueRepo){
        super(valueRepo);
    }

    @Override
    public void updateDecorator(Value entityDb, Value entity) {
        entityDb.setValue(entity.getValue());
        entityDb.setAttribute(entity.getAttribute());
        entityDb.setEntity(entity.getEntity());
    }

    @Override
    public void deleteAllByEntity_Id(Long id) {
        repository.deleteAllByEntity_Id(id);
    }

    @Override
    public void deleteAllByEntity_Name(String name) {
        repository.deleteAllByEntity_Name(name);
    }

    @Override
    public List<Value> findAllByEntity_Id(Long id) {
        List<Value> values = new ArrayList<>();
        repository.findAllByEntity_Id(id).forEach(values::add);
        return values;
    }

    @Override
    public List<Value> findAllByEntity_Name(String name) {
        List<Value> values = new ArrayList<>();
        repository.findAllByEntity_Name(name).forEach(values::add);
        return values;
    }
}
