package com.KotoPorot.Application_Demo.Repositories;

import com.KotoPorot.Application_Demo.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
