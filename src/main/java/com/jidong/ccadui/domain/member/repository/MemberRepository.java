package com.jidong.ccadui.domain.member.repository;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<com.jidong.ccadui.domain.member.repository.MbMemOAuth, Long> , MemberRepositoryCustom{
    MemberOAuth getMemberInfo(long memberNo);
    void insertMember(MemberOAuth memberOAuth);
}
