package com.ryzen.journal.cache;

import com.ryzen.journal.entity.DummyAPIConfigEntity;
import com.ryzen.journal.repository.DummyUserAPIRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class APICache {

    @Autowired
    private DummyUserAPIRepo dummyUserAPIRepo;


    public Map<String, String> API_CACHE = new HashMap<>();
    @PostConstruct
    public void init(){
        List<DummyAPIConfigEntity> list = dummyUserAPIRepo.findAll();
        for(DummyAPIConfigEntity dummyAPIConfigEntity: list){
            API_CACHE.put(dummyAPIConfigEntity.getKey(),dummyAPIConfigEntity.getValue());
        }
    }
}
