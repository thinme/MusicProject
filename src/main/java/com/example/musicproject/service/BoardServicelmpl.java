package com.example.musicproject.service;

import com.example.musicproject.domain.Board;
import com.example.musicproject.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//JPA가 @Serveice로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServicelmpl implements BoardService{

    @Autowired
    private BoardRepository boardRepo;
    //BoardRepository에 있는 db와 연동하여 기능하는 것을 명시
    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepositorydml
    //findAll메서드를 통해서 전체조회
    @Override
    public List<Board> getBoardList() {return  (List<Board>) boardRepo.findAll();}

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 save메서드
    //통해서 db에 저장(저장하는 SQL문 만들어서 실행)
    @Override
    public void insertBoard(Board board){boardRepo.save(board);}

    @Override
    public  Board getBoard(Board board){return boardRepo.findById(board.getSeq()).get();}

    @Override
    public void updateBoard(Board board){
        Board fIndBoard = boardRepo.findById(board.getSeq()).get();
        fIndBoard.setTitle(board.getTitle());
        fIndBoard.setContent(board.getContent());
        boardRepo.save(fIndBoard);
    }
    @Override
    public void deleteBoard(Board board) {boardRepo.deleteById(board.getSeq());}
}
