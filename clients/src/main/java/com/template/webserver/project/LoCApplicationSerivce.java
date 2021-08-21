package com.template.webserver.project;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LoCApplicationSerivce {

    public LoCApplicationEntity save(LoCApplicationEntity entity);
    public List<LoCApplicationEntity> findAll();

   // List<LoCApplicationEntity> findByLocStatus(String status);

}
