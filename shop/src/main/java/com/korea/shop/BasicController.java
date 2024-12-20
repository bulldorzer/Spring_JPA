package com.korea.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
//  @ResponseBody // 데이터로 전송할 경우
    String main(){
        return "index.html"; // 페이지 전송
    }

    // /about 접속하면 about.html 페이지를 보여주는 api 작성해보시오
    @GetMapping("/about")
    String about(){
        return "about.html";
    }
}
