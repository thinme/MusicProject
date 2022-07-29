package com.example.musicproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.musicproject.model.Board;

@Controller
public class BoardController {

        @RequestMapping("/getBoardList")
        public String getBoardList(Model model){
            //List 타입으로 Board객체를 넣는 boardList변수명 선언
            // =(대입연산자) heap메모리에 ArrayList타입으로 할당
            //List는 ArrayList의 부모클래스
            List<Board> boardList = new ArrayList<Board>();
            for(int i=1; i<=10; i++){
                //Board 클래스로 board인스턴스 생성
                Board board =new Board();
                //롬북으로 자동생성된 seter메서드로 데이터입력
                board.setSeq(new Long(i));
                board.setTitle("게시판 프로그램 테스트");
                board.setWriter("도우너");
                board.setContent("게시판 프로그램 테스트입니다");
                board.setCreateDate(new Date());
                board.setCnt(0L);
                //boardList배열에 board객체 넣기(for문 10번도니까 board객체 10개넣기)
                boardList.add(board);
            }
            model.addAttribute("boardList", boardList);
            //viewResolver
            //retrun getBoardList라는 문자열로 templates에 있는 같은 이름에 html파일을 찾는다
            return "getBoardList";
        }
}

