package com.KotoPorot.Application_Demo.Controllers;

import com.KotoPorot.Application_Demo.DTO.SubscriberDTO;
import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Role;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UserPrincipal;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import com.KotoPorot.Application_Demo.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.ok(boardService.createBoard(board, user));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addSubscriber")
    public ResponseEntity<List<SubscriberDTO>> addSubscriber(@RequestBody SubscriberDTO request,
                                                     Authentication authentication) {

//        if (!((UserPrincipal) authentication.getPrincipal()).getAuthorities().
//                        equals(new SimpleGrantedAuthority(Role.MANAGER.toString()))){
//            throw new IllegalArgumentException("User doesnt have right");
//        }

        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            String boardName = request.getBoardName();
            Users user = userService.findByUsername(request.getUsername());
            if (user != null) {
                return new ResponseEntity<>(boardService.addSubscriber(boardName, user), HttpStatus.CREATED);
            }
        }

        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/getBoardSubscribers/{id}")
    public ResponseEntity<List<SubscriberDTO>> getBoardSubscribers(@PathVariable Long id){
        Board board = boardService.findBoardById(id);
        if (board==null){
            return ResponseEntity.notFound().build();
        }
       try {
           return new ResponseEntity<>(boardService.getBoardSubscribers(board), HttpStatus.OK);
       } catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }
    }


}
