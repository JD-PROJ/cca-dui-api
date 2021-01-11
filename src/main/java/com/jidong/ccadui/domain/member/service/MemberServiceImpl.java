package com.jidong.ccadui.domain.member.service;

import com.jidong.ccadui.domain.member.repository.MemberRepository;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @NotNull
    private final MemberRepository memberRepository;

    @Override
    public MemberOAuth getMemberInfo(long memberNo) {
        return memberRepository.getMemberInfo(memberNo);
    }

    @Override
    public MemberOAuth getMemberInfoByServiceUserId(String serviceUserId) {
        return memberRepository.getMemberInfoByServiceUserId(serviceUserId);
    }

    @Override
    @Transactional
    public void insertMember(MemberOAuth memberOAuth) {
        memberRepository.insertMember(memberOAuth);
    }
}
