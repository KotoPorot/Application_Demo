package com.KotoPorot.Application_Demo.Login_registration.Controller;


import com.KotoPorot.Application_Demo.Login_registration.DTO.UsersRegistrationDTO;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String loginForm(@RequestBody UsersRegistrationDTO usersRegistrationDTO){
        return userService.verify(usersRegistrationDTO);
    }

    @GetMapping("")
public String homePage(){
        return "HELLO WORLD";
    }
}
