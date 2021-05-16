package com.jidong.ccadui.domain.calendar.repository;

import com.jidong.ccadui.domain.calendar.service.Schedule;
import com.jidong.ccadui.domain.calendar.service.ScheduleDetail;
import java.util.List;

public interface ScheduleRepository {
    List<Schedule> getSchedulesByMemberNo(long memberNo);

    List<ScheduleDetail> getScheduleDetail(long scheduleNo);
}
