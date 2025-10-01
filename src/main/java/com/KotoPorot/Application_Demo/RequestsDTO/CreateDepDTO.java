package com.KotoPorot.Application_Demo.RequestsDTO;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

public class CreateDepDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
