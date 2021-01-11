package com.jidong.ccadui.domain.member.repository;

import com.jidong.ccadui.domain.member.service.MemberOAuth;

public interface MemberRepositoryCustom {
    MemberOAuth getMemberInfo(long memberNo);
    MemberOAuth getMemberInfoByServiceUserId(String serviceUserId);
    void insertMember(MemberOAuth memberOAuth);
}
