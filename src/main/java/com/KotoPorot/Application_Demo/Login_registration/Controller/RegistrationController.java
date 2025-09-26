package com.KotoPorot.Application_Demo.Login_registration.Controller;


import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UsersRegistrationDTO;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody UsersRegistrationDTO userData){
        if(userService.findByUsername(userData.getUsername())==null){
        Users newUser = userService.addUser(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

}
