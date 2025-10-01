package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Users;

public class DepartmentMember {
    private Long id;
    private String username;

    public DepartmentMember(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
