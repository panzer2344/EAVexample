package com.azino.eav.service;

import com.azino.eav.model.eav.Attribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface AttributeService extends BaseService<Attribute> {

    Attribute findByName(String name);

    List<Attribute> getAllAttributesByEntityId(Long id);

}
