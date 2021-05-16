package com.jidong.ccadui.domain.calendar.service;

import com.jidong.ccadui.domain.calendar.repository.ScheduleRepository;
import java.util.List;
import java.util.Optional;
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
        return scheduleRepository.getSchedulesByMemberNo(memberNo)
                .stream()
                .filter(a -> a.setIsOwner())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<ScheduleDetail>> getScheduleDetails(long scheduleNo) {
        return Optional.ofNullable(scheduleRepository.getScheduleDetail(scheduleNo)
                .stream()
                .filter(a -> a.setIsOwner())
                .collect(Collectors.toList()));
    }
}
