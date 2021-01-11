package com.jidong.ccadui.domain.member.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbMemOauth is a Querydsl query type for MbMemOauth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMbMemOauth extends EntityPathBase<MbMemOauth> {

    private static final long serialVersionUID = 1682557592L;

    public static final QMbMemOauth mbMemOauth = new QMbMemOauth("mbMemOauth");

    public final com.jidong.ccadui.domain.QBaseTimeEntity _super = new com.jidong.ccadui.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final NumberPath<Long> memNo = createNumber("memNo", Long.class);

    public final StringPath svcNm = createString("svcNm");

    public final StringPath svcUsrId = createString("svcUsrId");

    public final StringPath svcUsrNm = createString("svcUsrNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    public QMbMemOauth(String variable) {
        super(MbMemOauth.class, forVariable(variable));
    }

    public QMbMemOauth(Path<? extends MbMemOauth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbMemOauth(PathMetadata metadata) {
        super(MbMemOauth.class, metadata);
    }

}

