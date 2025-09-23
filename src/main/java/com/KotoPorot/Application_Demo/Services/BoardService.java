package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.DTO.SubscriberDTO;
import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board, Users user) {
        board.setOwner(user.getUsername());
        board.addSubscriber(user);
        return boardRepository.save(board);
    }

    public List<SubscriberDTO> addSubscriber(String boardName, Users user) {
        Board board = boardRepository.findBoardByName(boardName);

        if (board != null) {
            board.addSubscriber(user);
            boardRepository.save(board);

            List<Users> users = board.getSubscribers();
            List<SubscriberDTO> subscriberDTOS = new ArrayList<>();
            for (Users subUser : users) {
                subscriberDTOS.add(new SubscriberDTO(subUser.getUsername(),subUser.getId(),
                        board.getName(), board.getId()));
            }
            return subscriberDTOS;
        } else {
            throw new IllegalArgumentException("Board not found");
        }
    }

    public List<SubscriberDTO> getBoardSubscribers(Board board){
        List<Users> users = board.getSubscribers();
        List<SubscriberDTO> subscriberDTOS = new ArrayList<>();
        for (Users user:users){
            subscriberDTOS.add(new SubscriberDTO(user.getUsername(), user.getId(),
                    board.getName(), board.getId()));
        }
        return subscriberDTOS;
    }


    public Board findBoardById(Long id) {
        return boardRepository.findBoardById(id);
    }
}
