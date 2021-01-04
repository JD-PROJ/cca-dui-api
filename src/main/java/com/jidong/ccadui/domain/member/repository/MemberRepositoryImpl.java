package com.jidong.ccadui.domain.member.repository;

import static com.jidong.ccadui.domain.member.repository.QMbMemOAuth.mbMemOAuth;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private final EntityManager entityManager;

    @Override
    public MemberOAuth getMemberInfo(long memberNo) {
        return queryFactory
                .select(Projections.constructor(MemberOAuth.class,
                        mbMemOAuth.memNo,
                        mbMemOAuth.svcNm,
                        mbMemOAuth.svcUsrId))
                .from(mbMemOAuth)
                .where(mbMemOAuth.memNo.eq(memberNo))
                .fetchOne();
    }

    @Override
    public void insertMember(MemberOAuth memberOAuth) {
        MbMemOauth mbMemOAuth = MbMemOauth.builder()
                .svcNm(memberOAuth.getServiceName())
                .svcNm(memberOAuth.getServiceUserId())
                .build();

        entityManager.persist(mbMemOAuth);
        entityManager.flush();
        entityManager.clear();
    }

}
