package com.example.musicproject.controller;

import com.example.musicproject.model.Piano;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller 선언을 해주면 Hello 클래스는 컨틀롤을 담당이라고 spring boot는 인식
@Controller
public class Hello {
    private final static String  main ="index";
    private final static String account="account";

    //@GetMapping 선언을 해주면 특정 (html에서 지정한)url로 인식해서 받아오게됨
    //일방통행 구조(페이지 전환(변경))
    @GetMapping("hello")
    public String hello(Model model) {
        Piano piano = new Piano();
        int soundData = piano.getSound();
        model.addAttribute("data",soundData);
        //아래 return hello는 템플릿에 hello.html로 이동
        //Controller는 return을 통해 '알아서' hello라는 이름의 html파일을 찾습니다
        return "hello";
    }
    //호출하는 클라이언트의 정보를 가져다가 서버(controller)에 전달 해주는 매핑
    //중괄호 {}안에 데이터를 컨트롤러에 전달 = url 이라는 변수의 데이터를 전달
    // @RequestMapping(get 방식) 어노테이션이 url정보를 갖고 있기 때문에
    //컨트롤러에서 데이터 매개변수를 받을 수 있다
    //클라이언트가 요청한 정보를 가져오는 어노테이션
    @RequestMapping(value = "account", method = RequestMethod.POST)
    public  String account(@RequestParam("id1")String id, Model model){
        ///클라이언트에서 받아온 id1변수이름의 데이터를
        //RequestParam의 데이터로 연산작업
        model.addAttribute("msg", "Hi" +id+"!!");
        //작업한 데이터를 model에 넣어서 클라이언트에 전송
        //model.addAttribute(Key, val)//value에는 배열,객체아무거나 넣을 수 있음
        model.addAttribute("id2",id);
        //static으로 선언한 문자열 변수를 return하여 String 메모리 절약
        return account;
        //ViewResolver를 통해 html확장자를 가진 제목의 문서를 비교해서 찾아감
    }

    @RequestMapping("∠")
    public String index(){
        return main;
    }
    @RequestMapping("/{url}")
    public String index(@PathVariable int url,Model model){
        int urlFix = url*2;
        model.addAttribute("msg","이 주소는 없는 경로 입니다. :" +urlFix);
        
        return main;
    }
}
