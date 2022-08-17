package com.example.musicproject.domain.account_info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Setter
@Getter
public class Member {

    //SELECT [*컬럼명=객체의 필드] FROM TABLE_NAME*객체;
    //CREATE TABLE(
//            seq NUMBER PRIMARY KEY,
//            ID  VACHAR2(40) NOT NULL
//    )
//    JPA 객체에 맞춰서 SQL문으로 바꿔주는 것(번역)
    //Id = table을 만들 때, 테이블의 튜플(row)을 식별할 수 있는 기본키
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 40)
    private String id;


    private String password;


    private String email;

    @Setter
    @Temporal(TemporalType.DATE)
    private Date creatDate;

    @Setter
    @Temporal(TemporalType.DATE)
    private  Date updateDate;
}
