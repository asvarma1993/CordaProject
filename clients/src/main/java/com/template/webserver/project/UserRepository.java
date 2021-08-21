package com.template.webserver.project;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT t FROM User t where t.username = ?1 AND t.password = ?2")
    public Optional<User> findByUsernameAndPassword(String username, String password);
}
