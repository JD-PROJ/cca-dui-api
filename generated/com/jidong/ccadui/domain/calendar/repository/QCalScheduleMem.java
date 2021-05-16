package com.jidong.ccadui.domain.calendar.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCalScheduleMem is a Querydsl query type for CalScheduleMem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalScheduleMem extends EntityPathBase<CalScheduleMem> {

    private static final long serialVersionUID = -225440235L;

    public static final QCalScheduleMem calScheduleMem = new QCalScheduleMem("calScheduleMem");

    public final com.jidong.ccadui.domain.QBaseTimeEntity _super = new com.jidong.ccadui.domain.QBaseTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final NumberPath<Long> createNo = createNumber("createNo", Long.class);

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final DateTimePath<java.util.Date> nonAvailableTime = createDateTime("nonAvailableTime", java.util.Date.class);

    public final NumberPath<Long> scheduleNo = createNumber("scheduleNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    public final NumberPath<Long> updateNo = createNumber("updateNo", Long.class);

    public QCalScheduleMem(String variable) {
        super(CalScheduleMem.class, forVariable(variable));
    }

    public QCalScheduleMem(Path<? extends CalScheduleMem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalScheduleMem(PathMetadata metadata) {
        super(CalScheduleMem.class, metadata);
    }

}

