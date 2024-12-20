package com.korea.shop;

import jakarta.persistence.*;
import lombok.Data;

// 클래스 필드들을 모두 테이블 구성 데이터로 변형하여 테이블을 구성함
@Entity
@Data
public class Item {
    // Primary_Key, Auto_INCREMENT (자동순번)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 긴문자열 저장 - columnDefinition = "TEXT"
    // not null - nullable = false
    @Column(length = 200, nullable = false)
    String title;
    Integer price;
}
