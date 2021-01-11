package com.jidong.ccadui.domain.member.service;

public interface MemberService {
    MemberOAuth getMemberInfo(long memberNo);
    MemberOAuth getMemberInfoByServiceUserId(String serviceUserId);
    void insertMember(MemberOAuth memberOAuth);
}
