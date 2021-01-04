package com.jidong.ccadui.domain.member.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbMemOAuth is a Querydsl query type for MemberOAuth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMbMemOAuth extends EntityPathBase<MbMemOauth> {

    private static final long serialVersionUID = 1681604280L;

    public static final QMbMemOAuth mbMemOAuth = new QMbMemOAuth("mbMemOAuth");

    public final com.jidong.ccadui.domain.QBaseTimeEntity _super = new com.jidong.ccadui.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final NumberPath<Long> memNo = createNumber("memNo", Long.class);

    public final StringPath svcNm = createString("svcNm");

    public final StringPath svcUsrId = createString("svcUsrId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    public QMbMemOAuth(String variable) {
        super(MbMemOauth.class, forVariable(variable));
    }

    public QMbMemOAuth(Path<? extends MbMemOauth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbMemOAuth(PathMetadata metadata) {
        super(MbMemOauth.class, metadata);
    }

}

