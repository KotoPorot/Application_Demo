package com.KotoPorot.Application_Demo.Repositories;

import com.KotoPorot.Application_Demo.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
