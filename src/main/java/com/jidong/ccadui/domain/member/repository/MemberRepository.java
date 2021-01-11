package com.jidong.ccadui.domain.member.repository;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MbMemOauth, Long> , MemberRepositoryCustom{
    MemberOAuth getMemberInfo(long memberNo);
    MemberOAuth getMemberInfoByServiceUserId(String serviceUserId);
    void insertMember(MemberOAuth memberOAuth);
}
