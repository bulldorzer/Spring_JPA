package com.korea.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    // 기본에 이미 객체 싱글톤 생선되어서 - container가 관리중
    // 신규생성X, 기존에 이미 생성된 객체를 넣어줌.
    private final ItemRepository itemRepository;

    public void showList(Model model){
        List<Item> items = itemRepository.findAll(); // select * from item;
        model.addAttribute("items",items);
    }

    public void writePost(String title, int price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void editPost(Long id, String title, int price){
        Item item = itemRepository.findById(id).orElse(new Item());
        item.setId(id);
        item.setPrice(price);
        item.setTitle(title);

        itemRepository.save(item); // 데이터가 존재x 신규추가 / 존재 0 - 수정
    }

    public Optional<Item> findItemById(Long id){
        return itemRepository.findById(id);
    }

}
