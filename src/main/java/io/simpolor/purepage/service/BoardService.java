package io.simpolor.purepage.service;

import io.simpolor.purepage.repository.BoardRepository;
import io.simpolor.purepage.repository.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public long getTotalCount() {
        return boardRepository.count();
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board get(long id) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if(!optionalBoard.isPresent()){
            throw new IllegalArgumentException("Notfound Id");
        }
        return optionalBoard.get();
    }

    public Board register(Board board) {

        return boardRepository.save(board);
    }

    public Board modify(Board board) {

        Optional<Board> optionalBoard = boardRepository.findById(board.getId());
        if(!optionalBoard.isPresent()){
            throw new IllegalArgumentException("Notfound Id");
        }
        return boardRepository.save(board);
    }

    public void delete(long id) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if(!optionalBoard.isPresent()){
            throw new IllegalArgumentException("Notfound Id");
        }

        boardRepository.deleteById(id);
    }

}
