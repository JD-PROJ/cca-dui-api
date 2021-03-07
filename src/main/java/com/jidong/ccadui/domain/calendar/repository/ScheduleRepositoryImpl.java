package com.jidong.ccadui.domain.calendar.repository;

import static com.jidong.ccadui.domain.calendar.repository.QCalSchedule.calSchedule;
import static com.jidong.ccadui.domain.calendar.repository.QCalScheduleMem.calScheduleMem;

import com.jidong.ccadui.domain.calendar.service.Schedule;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JPAQueryFactory queryFactory;

    private final EntityManager entityManager;

    @Override
    public List<Schedule> getSchedulesByMemberNo(long memberNo) {
        return queryFactory
                .select(Projections.constructor(Schedule.class,
                        calSchedule.scheduleNo,
                        calSchedule.scheduleTitle,
                        calSchedule.scheduleOwnerNo,
                        calSchedule.scheduleStatus,
                        calSchedule.beginDate,
                        calSchedule.endDate,
                        calSchedule.confirmDate,
                        calSchedule.createNo
                ))
                .from(calSchedule)
                .join(calScheduleMem)
                .on(calSchedule.scheduleNo.eq(calScheduleMem.scheduleNo))
                .where(calScheduleMem.memberNo.eq(memberNo))
                .fetch();
    }

}
