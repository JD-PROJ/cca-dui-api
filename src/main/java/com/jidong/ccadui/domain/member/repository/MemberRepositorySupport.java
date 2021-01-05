package com.jidong.ccadui.domain.member.repository;

import static com.jidong.ccadui.domain.member.repository.QMbMemOauth.mbMemOauth;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {

    private final JPQLQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(MemberOAuth.class);
        this.queryFactory = queryFactory;
    }

    public MbMemOauth getMemberInfo(long memberNo) {
         return queryFactory
               .selectFrom(mbMemOauth)
               .where(mbMemOauth.memNo.eq(memberNo))
               .fetchOne();
    }

    public void  insertMember(MbMemOauth mbMemOAuth) {

    }
}
