package com.jidong.ccadui.domain.member.repository;

import static com.jidong.ccadui.domain.member.repository.QMbMemOauth.mbMemOauth;

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
                        mbMemOauth.memNo,
                        mbMemOauth.svcNm,
                        mbMemOauth.svcUsrId,
                        mbMemOauth.svcUsrNm))
                .from(mbMemOauth)
                .where(mbMemOauth.memNo.eq(memberNo))
                .fetchOne();
    }

    @Override
    public MemberOAuth getMemberInfoByServiceUserId(String serviceUserId) {
        return queryFactory
                .select(Projections.constructor(MemberOAuth.class,
                        mbMemOauth.memNo,
                        mbMemOauth.svcNm,
                        mbMemOauth.svcUsrId,
                        mbMemOauth.svcUsrNm))
                .from(mbMemOauth)
                .where(mbMemOauth.svcUsrId.eq(serviceUserId))
                .fetchOne();
    }

    @Override
    public void insertMember(MemberOAuth memberOAuth) {
        MbMemOauth mbMemOAuth = MbMemOauth.builder()
                .memNo(memberOAuth.getMemberNo())
                .svcNm(memberOAuth.getServiceName())
                .svcUsrId(memberOAuth.getServiceUserId())
                .build();

        entityManager.persist(mbMemOAuth);
        entityManager.flush();
        entityManager.clear();
    }

}
