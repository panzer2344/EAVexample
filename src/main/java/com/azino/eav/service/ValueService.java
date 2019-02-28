package com.azino.eav.service;

import com.azino.eav.model.eav.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ValueService extends BaseService<Value> {

    void deleteAllByEntity_Id(Long id);

    void deleteAllByEntity_Name(String name);

    List<Value> findAllByEntity_Id(Long id);

    List<Value> findAllByEntity_Name(String name);

}
