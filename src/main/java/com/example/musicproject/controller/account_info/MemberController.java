package com.example.musicproject.controller.account_info;

import com.example.musicproject.domain.account_info.Member;
import com.example.musicproject.service.account_info.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

//디스패쳐서블릿 이 컨트롤러를 찾기위해서 @Controller라고 선언
@Controller
@RequestMapping(path="/account")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    //(클라이언트가 두분류)게시판 사용자관점,시스템관점
    //게시판  사용자관점, 시스템관점(회원관리, 게시판관리, 컨텐츠관리)[웹솔루션을 관리하는 오너]
    //getAccountList : 전체  회원 목록 보기 : 웹솔루션에서 웹시스템을 관리하는 관리자를 하는기능
    //public 전부공개
    //String 이메서드가 실행 완료되면 최종적으로 리턴하는 타입(HTML 파일명을 찾기위해서)

    @GetMapping("/getAccountList")
    public String getAccountList(Model model){
        //model 컨트롤러에서 작업한 결과물을 HTML에 전달하기 위한 매개체
        //addAttribute Key/value으로 데이터를 저장하는 메서드
        //attributeName(Key) 뒤에 있는 value를 호출하기 위한 문자열(key)
        //memberService.getMemberList() @Autowired로 선언된 MemberService 클래스를 호출하여
        //getMemberList()메서드 실행
        model.addAttribute("memberlist", memberService.getMemberList());
        return "account/getAccountList";
    }
    //deleteAccount ; 회원정보삭제
    //updateAccount : 회원정보수정

    //+백업 entity
    //회원정보가 일정 수치까지 다다르면(혹은 이벤트가 발생0 updateAccount이라는 메서드를 통해
    //기존 entity의 테이블에 모두 저장(벡엔 entity에 저장)

    //회원정보 1개의 테이블 관리x--->1년 지나도록 로그인 안한 장기휴식회원
    //+1년 미접속 계정은 따로 테이블을 옮겨서 저장(예전방식)
    //날짜별로 1년이 지나면 새로 테이블을 생성해서 회원을 관리합니다(회원가입한 날짜)
    //>>최신회원들을 관리한 마케팅 부서에 도움
    //DB 백업할때에도 테이블 파편화로 트랙잭션 위험 또는 시간 절약
    //단점  Admin(관리자)는 모든 회원정보를 볼 때 다수의 테이블을 동시에 봐야 하기때문에 join을 써서 속도가 느림림
    //eturn 타입이 String인 이유: HTML파일 명을 찾기위해
    @GetMapping("/insertAccount")
    public String insertAccountView(){
        return "/account/insertAccount";
    }

    //Member라는 매개변수로 controller에 전달
    //Member(Entity)이고 DTO(Data Transfer Object)
    //어디선가 받거나 만든 데이터를 객체로 만드는것 -> DTO
    @PostMapping("/insertAccount")
    public  String insertAccountView(Member member){
        //클라이언트에서 ID/PW
        //createDate
        //updateDate
        member.setCreatDate(new Date());
        member.setUpdateDate(new Date());
        memberService.insertMember(member);
        return "redirect:/account/getAccountList";
    }

    @GetMapping("/getAccount")
    public  String getAccount(Member member, Model model){
        model.addAttribute("member", memberService.getMember(member));
        return "/account/getAccount";

    }
    @GetMapping("/updateAccount")
    public String updateAccount(Member member){
        memberService.updateMember(member);
        return "redirect:/account/getAccountList";
    }
    @GetMapping("/deleteAccount")
    public String deleteAccount(Member member){
        memberService.deleteMember(member);
        return "redirect:/account/getAccountList";
    }
    @GetMapping("/selectAccount")
    public String selectAccount(){return  "account/selectAccount";}

    @PostMapping("/selectAccount")
    public String resultAccount(Member member, Model model){
        model.addAttribute("memberList",
                memberService.getMemberWhereIdOrEmail(member.getId(), member.getEmail()));
        model.addAttribute("member",
                memberService.getMemberFindFirstEmailLike(member.getEmail()));
        return "account/resultAccount";
    }

}
