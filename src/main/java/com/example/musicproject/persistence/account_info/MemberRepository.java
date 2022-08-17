package com.example.musicproject.persistence.account_info;

import com.example.musicproject.domain.account_info.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository  DB에 기본적인 SQL문을 통해 소통(INSERT INTO, SELECT, UPDATE, DELETE)
public interface MemberRepository extends JpaRepository<Member, Long> {


//    List<Member> findByIdOrEmail(String email);

    //Return 내용선언, find~변수명에 맞춰서 메서드 생성, 필요한 매개변수
    //쿼리문에서는 *(모든것)인데 여기에서는 안먹혀서 m이라고 써줌(문법상 별칭)
    @Query(value = "select m from Member m where  m.email = :email_1 or m.id = :id_1")
    Member findByMeberByEmailOrId(String email_1, String id_1);

    //(ID는 중복가능한 구조에서)Id값을 매개변수로 넣고, 아이디 생성날짜가 가장 최신 인 것
    @Query(value = "select m from Member m where m.id = :id_1 order by m.creatDate DESC")
    Member findFirstById(String id_1);

    @Query(value = "select m from Member m where m.email like :___% ")
    Member findFirstEmailLike(String email_1);
}

