package com.jidong.ccadui.domain.member.repository;

import static com.jidong.ccadui.domain.member.repository.QMbMemOAuth.mbMemOAuth;

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

    public MbMemOAuth getMemberInfo(long memberNo) {
         return queryFactory
               .selectFrom(mbMemOAuth)
               .where(mbMemOAuth.memNo.eq(memberNo))
               .fetchOne();
    }

    public void  insertMember(com.jidong.ccadui.domain.member.repository.MbMemOAuth mbMemOAuth) {

    }
}
