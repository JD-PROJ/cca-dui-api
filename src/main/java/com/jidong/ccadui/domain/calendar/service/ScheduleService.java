package com.jidong.ccadui.domain.calendar.service;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedulesByMemberNo(long memberNo);
}
