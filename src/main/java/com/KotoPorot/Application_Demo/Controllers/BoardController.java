package com.KotoPorot.Application_Demo.Controllers;

import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UserPrincipal;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import com.KotoPorot.Application_Demo.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @PostMapping("/createBoard")
    public ResponseEntity<Board> createBoard(@RequestBody Board board, Authentication authentication) {
        System.out.println("Authenticated: " + (authentication != null && authentication.isAuthenticated()));
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users user = userService.findByUsername(userPrincipal.getUsername());
            return ResponseEntity.ok(boardService.createBoard(board, userPrincipal.getUsername()));
        }
        return ResponseEntity.badRequest().build();
    }

}
