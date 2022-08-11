package com.example.musicproject.domain;

//외장 라이브러리
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import  java.util.Date;
@Getter
@Setter
@ToString
@Builder
//@Entity 이 클래스가 jPA를 통해 데이터베이스 테이블로 쓰겠다고 명시해주는 속성
@Entity
@AllArgsConstructor
@NoArgsConstructor
//원래는 getter,setter라는 메서드가 있어야 private가 가능
//하지만 롬복 외장라이브러리를 통해 getter,setter를 안써도 자동적용
public class Board {
    //@Id PK(기본키) SQL문의 기본키
    //@GeneratedValue 자동생성 속성
    @GeneratedValue @Id
    private  Long seq;

    //@Column은 title 필드값 컬럼화 할때 길이와 null 입력 가능여부옵션
    @Column(length = 40, nullable = false)
    private  String title;

    @Column(nullable = false, updatable = false)
    private  String writer;

    //@ColumnDefault 생성 할때 기본데이터
    @Column(nullable = false)
    @ColumnDefault("no content")
    private  String content;
    private  String content_20;

    @Temporal(TemporalType.DATE)
    private  Date createDate;

    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private  Long cnt;
}
