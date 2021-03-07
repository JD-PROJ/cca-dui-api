package com.jidong.ccadui.domain.calendar.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCalSchedule is a Querydsl query type for CalSchedule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalSchedule extends EntityPathBase<CalSchedule> {

    private static final long serialVersionUID = 921959328L;

    public static final QCalSchedule calSchedule = new QCalSchedule("calSchedule");

    public final com.jidong.ccadui.domain.QBaseTimeEntity _super = new com.jidong.ccadui.domain.QBaseTimeEntity(this);

    public final DateTimePath<java.util.Date> beginDate = createDateTime("beginDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> confirmDate = createDateTime("confirmDate", java.util.Date.class);

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final StringPath createNo = createString("createNo");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> scheduleNo = createNumber("scheduleNo", Long.class);

    public final NumberPath<Long> scheduleOwnerNo = createNumber("scheduleOwnerNo", Long.class);

    public final StringPath scheduleStatus = createString("scheduleStatus");

    public final StringPath scheduleTitle = createString("scheduleTitle");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    public final StringPath updateNo = createString("updateNo");

    public QCalSchedule(String variable) {
        super(CalSchedule.class, forVariable(variable));
    }

    public QCalSchedule(Path<? extends CalSchedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalSchedule(PathMetadata metadata) {
        super(CalSchedule.class, metadata);
    }

}

