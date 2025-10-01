package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDTO {
    private Long id;
    private String name;
    private String respManager;
    private Long respManagerId;
    private List<DepartmentMember> depMembers;

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.depMembers = department.getMembers().stream().map(DepartmentMember::new).collect(Collectors.toList());

        if(department.getResponsibleManager()!=null){
        this.respManager=department.getResponsibleManager().getUsername();
        this.respManagerId=department.getResponsibleManager().getId();
        }else {
            this.respManagerId = null;
            this.respManager = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentMember> getDepMembers() {
        return depMembers;
    }

    public void setDepMembers(List<DepartmentMember> depMembers) {
        this.depMembers = depMembers;
    }

    public String getRespManager() {
        return respManager;
    }

    public void setRespManager(String respManager) {
        this.respManager = respManager;
    }

    public Long getRespManagerId() {
        return respManagerId;
    }

    public void setRespManagerId(Long respManagerId) {
        this.respManagerId = respManagerId;
    }
}
