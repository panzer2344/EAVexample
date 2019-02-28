package com.azino.eav.service.impl;

import com.azino.eav.model.IModel;
import com.azino.eav.service.AttributeService;
import com.azino.eav.service.BaseEntitiesService;
import com.azino.eav.service.EntityService;
import com.azino.eav.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseEntitiesServiceImpl<T extends IModel> implements BaseEntitiesService<T> {

    @Autowired
    protected AttributeService attributeService;

    @Autowired
    protected ValueService valueService;

    @Autowired
    protected EntityService entityService;

}
