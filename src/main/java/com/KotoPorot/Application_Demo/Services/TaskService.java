package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.Entities.Task;
import com.KotoPorot.Application_Demo.Repositories.DepRepository;
import com.KotoPorot.Application_Demo.Repositories.TaskRepository;
import com.KotoPorot.Application_Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private DepRepository depRepository;
    @Autowired
    private UserRepository userRepository;

    public Task saveTask(Task task) {
        System.out.println("before save: "+task);
       task = taskRepository.save(task);
        System.out.println("after save: "+task);
       return task;

    }

}
