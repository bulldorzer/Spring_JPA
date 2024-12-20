package com.exam.mall;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/user")
    String user(Model model){
        model.addAttribute("name", "홍길동");
        model.addAttribute("age", 30);
        return "user.html";
    }
}
