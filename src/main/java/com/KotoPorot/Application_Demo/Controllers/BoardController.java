package com.KotoPorot.Application_Demo.Controllers;

import com.KotoPorot.Application_Demo.RequestsDTO.MemberDTO;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateBoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.UserRolesDTO;
import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UserPrincipal;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import com.KotoPorot.Application_Demo.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @PostMapping("/createBoard")
    public ResponseEntity<UserRolesDTO> createBoard(@RequestBody CreateBoardDTO board, @NonNull Authentication authentication) {
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserPrincipal) {
            Users user = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            if (boardService.findBoardByName(board.getBoardName()) != null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            return ResponseEntity.ok(boardService.createBoard(board.getBoardName(), user));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addBoardMember")
    public ResponseEntity<List<UserRolesDTO>> addSubscriber(@RequestBody MemberDTO request,
                                                            Authentication authentication) {

        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            Users executor = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            System.out.println(executor);
            Board board = boardService.findBoardByName(request.getBoardName());
            if (!boardService.isUserCanChangeBoard(executor, board)) {
                System.out.println("cant change");
               return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            }
                Users user = userService.findByUsername(request.getUserName());
                if (board == null || user == null || boardService.isUserMember(board, user)) {
                    System.out.println(board);
                    System.out.println(user);
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
                List<UserRolesDTO> members = boardService.addSubscriber(board, user);
                return ResponseEntity.ok(members);
            }
        return ResponseEntity.badRequest().build();
        }



//    @PostMapping("/deleteBoardMember")
//    public ResponseEntity<List<UserRolesDTO>> deleteMember(@RequestBody MemberDTO request,
//                                                           Authentication authentication){
//
//
//
//    }


//    @GetMapping("/getBoardSubscribers/{id}")
//    public ResponseEntity<List<SubscriberDTO>> getBoardSubscribers(@PathVariable Long id){
//        Board board = boardService.findBoardById(id);
//        if (board==null){
//            return ResponseEntity.notFound().build();
//        }
//       try {
//           return new ResponseEntity<>(boardService.getBoardSubscribers(board), HttpStatus.OK);
//       } catch (Exception e) {
//           return ResponseEntity.badRequest().build();
//       }
//    }


}
