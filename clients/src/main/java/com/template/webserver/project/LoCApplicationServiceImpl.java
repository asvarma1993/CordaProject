package com.template.webserver.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoCApplicationServiceImpl implements LoCApplicationSerivce {
    @Autowired
    LoCApplicationRepository loCApplicationRepository;

    public LoCApplicationEntity save(LoCApplicationEntity entity){
        return loCApplicationRepository.save(entity);

    }

    @Override
    public List<LoCApplicationEntity> findAll() {
        return loCApplicationRepository.findAll();
    }

    /*@Override
    public List<LoCApplicationEntity> findByLocStatus(String status) {
        return loCApplicationRepository.findByLocStatus(status);
    }*/


}
