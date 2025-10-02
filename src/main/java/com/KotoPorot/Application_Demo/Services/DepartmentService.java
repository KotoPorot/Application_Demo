package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Department;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Repositories.BoardRepository;
import com.KotoPorot.Application_Demo.Repositories.DepRepository;
import com.KotoPorot.Application_Demo.Repositories.UserRepository;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateDepRequestDTO;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepRepository depRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Department createDepartment(CreateDepRequestDTO depDTO, Board board) {
        Department existDep = board.getDepartments().stream()
                .filter(dep -> dep.getName().equals(depDTO.getName()))
                .findFirst().orElse(null);

        if (existDep != null) {
            return null;
        } else {
            board.getDepartments().add(new Department(depDTO, board));
            board = boardRepository.save(board);
            return board.getDepartments().getLast();
        }
    }


    public Department findDepartmentById(Long departmentId) {
        return depRepository.findById(departmentId).orElse(null);
    }

    @Transactional
    public List<Users> addMember(Department department, Users member) {
        department.getMembers().add(member);
        member.getDepartments().add(department);

        department = depRepository.save(department);
        return department.getMembers();

    }

    public boolean isMember(Users member, Department department) {
        if (department.getMembers().contains(member)) {
            return true;
        } else return false;

    }

    public List<Users> deleteMember(Department department, Users member) {
        member.getDepartments().remove(department);
        department.getMembers().remove(member);
        department = depRepository.save(department);
        return department.getMembers();
    }
}
