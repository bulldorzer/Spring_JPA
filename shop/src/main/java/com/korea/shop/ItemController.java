package com.korea.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*
        db에서 데이터 꺼내오기
        1. Repository interface 만들기 (interface 생성)
        2. DB입출력 원하는 <클래스>에서 repository를 등록 (객체 선언)
        3. DB 입출력 문법 사용하기 (메서드 호출)

        외부에서 객체를 생성자에게 넘겨주면
        생성자가 멤버변수 값에 설정 초기화

        스트링부트가 객체를 하나 생성해서(싱클톤)
        변수에 연결해 놓음 (JpaRepository 상속받은 인터페이스 객체에 연결)
    */


// 생성자
// Object(객체) 하나 뽑아서 설정해줘.
// 클래스 상단에 적인 어노테이션 두개로 모두 됨
    /*@Autowired
    ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }*/

@Controller
// 알아서 객체(생글톤) 하나 뽑아서 itemRepository에 연결해줘
// final 또는 @NonNUll이 붙은 멤버 변수들을 초기화하는 생성자를 자동으로 생성
@RequiredArgsConstructor 
public class ItemController {



    // 상품목록
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){

        List<Item> items = itemRepository.findAll(); // select * from item;
        model.addAttribute("items",items);

        return "list.html";
    }
}
