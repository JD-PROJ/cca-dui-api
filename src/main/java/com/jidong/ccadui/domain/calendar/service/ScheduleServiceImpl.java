package com.jidong.ccadui.domain.calendar.service;

import com.jidong.ccadui.domain.calendar.repository.ScheduleRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    @NotNull
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getSchedulesByMemberNo(long memberNo) {

        List<Schedule> schedules = scheduleRepository.getSchedulesByMemberNo(memberNo);
        return schedules.stream().filter(a -> a.setIsOwner()).collect(Collectors.toList());
    }
}
