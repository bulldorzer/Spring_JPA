package com.exam.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicContoller {

    @GetMapping("/") // 처리할 URL
    @ResponseBody
    String start(){ // 메서드 이름 작명
        return "<h1>메인페이지</h1>"; // 유저에게 보내는 데이터
    }

    @GetMapping("/about") // 처리할 URL
    String about(){ // 메서드 이름 작명
        return "about.html"; // 유저에게 보내는 데이터
//        return "<h1>about</h1>"; // 유저에게 보내는 데이터
    }
}
