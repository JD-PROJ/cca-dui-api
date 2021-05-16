package com.jidong.ccadui.domain.calendar.service;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getSchedulesByMemberNo(long memberNo);
    Optional<List<ScheduleDetail>> getScheduleDetails(long scheduleNo);
}
