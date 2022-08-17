package com.example.musicproject.service.account_info;

import com.example.musicproject.domain.account_info.Member;

import java.util.List;

public interface MemberService {

    Member getMemberFindFirstEmailLike(String Email);

    Member getMemberWhereIdOrEmail(String Id, String Email);

    Member getMemberWhereIdAndROWNUL1(String Id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);
}
