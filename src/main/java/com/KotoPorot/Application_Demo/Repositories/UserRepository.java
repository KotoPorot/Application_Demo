package com.KotoPorot.Application_Demo.Repositories;

import com.KotoPorot.Application_Demo.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * FROM users WHERE BINARY username = ?1", nativeQuery = true)
    Users findByUsername(String username);
}
