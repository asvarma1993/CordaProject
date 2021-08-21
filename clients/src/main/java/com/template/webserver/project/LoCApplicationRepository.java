package com.template.webserver.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoCApplicationRepository extends JpaRepository<LoCApplicationEntity, Integer> {
/*

    @Query("SELECT t FROM LoCApplication t WHERE t.fooIn = ?1")
    List<LoCApplicationEntity> findByLocStatus(String status);
*/

}
