package com.korea.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/*
        db에서 데이터 꺼내오기
        1. Repository interface 만들기 (interface 생성)
            - 데이터를 저장할 클래스의 이름 + Repository 인터페이스 생성
            - JpaRepository<클래스명, Long> - 상속받기 (필요 메서드 포함되어있음)
            public interface ItemRepository extends JpaRepository<Item, Long> {
            }
        
        2. DB입출력 원하는 <클래스>에서 repository를 등록 (객체 선언)
            - 클래스명 위에 @RequiredArgsConstructor 어노테이션 추가
            - 클래스 멤버 변수로 private final 클래스명 객체명; 선언하기
            ex) private final ItemRepository itemRepository;
            
        3. DB 입출력 문법 사용하기 (메서드 호출)
            - 객체명.findAll();
            - 객체명.save() - 저장 또는 수정;
            ex) itemRepository.findAll();

        외부에서 객체를 생성자에게 넘겨주면
        생성자가 멤버변수 값에 설정 초기화

        스트링부트가 객체를 하나 생성해서(싱클톤)
        변수에 연결해 놓음 (JpaRepository 상속받은 인터페이스 객체에 연결)
    */


// 생성자
// Object(객체) 하나 뽑아서 설정해줘.
// 클래스 상단에 적인 어노테이션 두개로 모두 됨
    /*
        @Autowired
        ItemController(ItemRepository itemRepository){
            this.itemRepository = itemRepository;
        }
    */

@Controller
// final 또는 @NonNUll이 붙은 멤버 변수들을 초기화하는 생성자를 자동으로 생성
@RequiredArgsConstructor
public class ItemController {



    // 상품목록
    // 알아서 객체(생글톤) 하나 뽑아서 itemRepository에 연결해줘
    private final ItemRepository itemRepository;
    private final ItemService itemService; // 데이터 전송 전 로직 = 서비스

    @GetMapping("/list")
    String list(Model model){

        itemService.showList(model);

        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/add")
    String writePost(String title, int price){

        itemService.writePost(title,price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        // Optional 클래스 - Null값으로 인한 오류를 방지, 명시적으로 값이 없을을 표현하는데 사용
        // id 기준으로 데이터 검색
        Optional<Item> result = itemService.findItemById(id);

        if (result.isPresent()){ // 값 유무 확인
            // get() 메서드를 써야 데이터 출력함
            // model에 item이라는 이름으로 데이터 담음
            model.addAttribute("item", result.get());

            // 페이지로 response함
            return "detail.html";
        }else {
            return "redirect:/list";
        }

    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){
        // 데이터의 고유값 -id - id 값을 주소를 통해서 받아옴
        // id를 기준으로 데이터를 검색
        // 검색한 데이터를 edit.html에 보내준다
        // 데이터를 담을때는 model 담아서 보낸다. - model.addAttribute(k, v);
        Optional<Item> result = itemService.findItemById(id);
        if(result.isPresent()){
            model.addAttribute("data",result.get());
            return "edit.html";
        }else {
            return "redirect:/list";
        }

    }

    @PostMapping("/edit")
    String editItem(Long id, String title, int price){
        /*
             itemRepository.findById(id) = Optional 자료형으로 리턴
             Optional 메소드 중에서
             isPresent(); - 값이 존재하는지 확인 true/false로 결과
             orElse(자료형 객체형) - 값이 존재하면 해당값을 존재안하면 설정값으로 리턴
        */

//        Optional<Item> result = itemRepository.findById(id);
//        result.orElse(new Item);
        itemService.editPost(id,title,price);

        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteItem(@PathVariable Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.ok("Item deleted successfully");
    }



}

