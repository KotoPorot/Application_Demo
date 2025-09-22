package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board, String username) {
        board.setOwner(username);
        return boardRepository.save(board);
    }
}
