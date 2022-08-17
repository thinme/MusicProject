package com.example.musicproject.service.account_info;

import com.example.musicproject.domain.account_info.Member;
import com.example.musicproject.persistence.account_info.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeberServicelmpl implements MemberService {

    //데이터베이스와 연동되는JPA를 명시

    private final MemberRepository memberRepo;

    @Autowired
    protected MeberServicelmpl(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    //모든회원을 정보를 가져다 오는 것
    //return List<Member> 모든 회원의 정보를 List배열에 담아서 return

    //public 공개
    //List<Member> 리턴타입은 List 속성은 Member
    //return memberRepository의 findMemberByEmailOrId메서드를 실행한 리턴 데이터
    @Override
    public Member getMemberWhereIdOrEmail(String id, String email) {
        return memberRepo.findByMeberByEmailOrId(id,email);
    }

    @Override
    public Member getMemberWhereIdAndROWNUL1(String id) {
        return memberRepo.findFirstById(id);
    }

    @Override
    public Member getMemberFindFirstEmailLike(String Email){return memberRepo.findFirstEmailLike(Email);}

    //public 모두 공개하는 메서드
    //List<Member> 이 메서드가 실행되면 return되는 타입
    //(List<Member>) 뒤에 있는 결과값 형변환
    //memberRepo @Autowired MemberRepository를 통해 기능 실행
    //findAll() memberRepo에 모든 정보 가져오기 실행
    @Override
    public List<Member> getMemberList() {

        return (List<Member>) memberRepo.findAll();
    }
    //회원 1명의 정보를 Entity에 맞춰서 DB에 저장
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }


    @Override
    public Member getMember(Member member) {
        return memberRepo.findById(member.getSeq()).get();
    }

    @Override
    public void updateMember(Member member) {
        //1.seq를 통해서 튜플 정보를 모두 가져오기
        //2.가져온 튜플 정보 중 수정할 내용 적용
        //3.DB에 저장(덮어쓰기)
        //findById().get() 고유키 기준으로 튜플 전체 데이터 가져오기
        Member findMember = memberRepo.findById(member.getSeq()).get();
        //튜플 전체 내용중에 id/emaill주소 수정(setter)
        findMember.setId(member.getId());
        findMember.setEmail(member.getEmail());
        //crudRepo의 save메서드를 통해 데이터저장
        memberRepo.save(findMember);

        //고유키
        //1.튜플을 식별할 수 있는 값(데이터 한 줄) DB관점에서 보는것
        //2.다른테이블의 튜플과 연동하기 위한 값(JOIN, 외래키)
        //3.객체지향 방법으로 db를 저장
        //3-1. 영속성 db에 영구저장
        //3-2. 고립성 다른 트랙젝션 작업에 연관되지않도록 해주는것
        //3-2-0. seq 10의 회원정보가 바꿨습니다. 이미 접속해 있던 관리자 2가 seq10 회원의 정보를 조회합니다
        //seq10의 회원정보를 바꾸는 작업이 한 개의 트랜잭션, 관리자2의 seq10회원의 정보를 조회하고 수정하는 작업 한개의 트랜잭션
        //관리자1의 트랜잭션 작업이 완료되는 순간까지 관리자2는 seq10회원의 정보를 온전히 수정 할 수없다.
        //다른 필드값은 수정이 가능해도, seq는 튜플의 식별자로써 수정이 불가해야, 관리자 1,2의 트랜잭션 작업을 스프링 부트에서 구분 할 수 있다.

    }

    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getSeq());

    }
}
