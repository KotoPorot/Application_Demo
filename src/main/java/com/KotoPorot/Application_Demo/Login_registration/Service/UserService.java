package com.KotoPorot.Application_Demo.Login_registration.Service;


import com.KotoPorot.Application_Demo.Entities.Role;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UsersRegistrationDTO;
import com.KotoPorot.Application_Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users addUser(UsersRegistrationDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        Users newUser = new Users();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(Role.USER);          //DEFAULT!
        return userRepository.save(newUser);
    }

    public String verify(UsersRegistrationDTO user) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }else {
            return "false";
        }

    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}