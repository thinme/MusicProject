package com.example.musicproject.controller;
//외장 라이브러리 호출(import), gradle로 설치한 라이브러리

import com.example.musicproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//내장 라이브러리 호출

import java.util.Date;
import java.util.List;
import com.example.musicproject.domain.Board;


@Controller
public class BoardController {


    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public String getBoardList(Model model){
        List<Board> boardList= boardService.getBoardList();
        model.addAttribute("boardList", boardList);
       return"getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "getBoard";
    }

     //@GetMapping 또는 @PostMapping은 @RequestMapping의 자식 클래스이다
     //@RequestMapping의 기능을 모두 쓸수있다.
     // 자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
     // 서버의 리소스를 감소및 보안을 위해서 한정된 기능을 쓰는것이다
     @GetMapping("insertBoard")
     public String insertBoard(){return "insertBoard";}

    //[클라이언트]html form태그의 method속성값인 post를 인식하여 아래의
    //@PostMapping에 연결
    @PostMapping("insertBoard")
    //클라이언트에서 board객체를 받아서 매개변수사용
    //[1]BoardService의 insertBoard메서드 실행
    //[2]BoardRepository(CrudRepository).save(board)를 통해서(JPA 번역)
    //DB의 데이터 모두 불러오기(테이블전체)
    //insertBoard라는 메서드에 board객체 인자값으로 넣기
    public String insertBoard(Board board) {
        board.setCreateDate(new Date());
        boardService.insertBoard(board);

         return "redirect:getBoardList";
    }
    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "insertBoard";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }



         /*
         * Board domain boardContoroller
         * @return String HTML파일과 연결(viewResolver)
         * @author  작성자 이름
         * @version 날짜+버전번호
          */
}

