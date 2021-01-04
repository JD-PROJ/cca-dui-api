package com.jidong.ccadui.domain.member.repository;

import com.jidong.ccadui.domain.member.service.MemberOAuth;

public interface MemberRepositoryCustom {
    MemberOAuth getMemberInfo(long memberNo);
    void insertMember(MemberOAuth memberOAuth);
}
