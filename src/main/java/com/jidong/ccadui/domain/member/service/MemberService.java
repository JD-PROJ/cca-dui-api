package com.jidong.ccadui.domain.member.service;

public interface MemberService {
    MemberOAuth getMemberInfo(long memberNo);
    void insertMember(MemberOAuth memberOAuth);
}
