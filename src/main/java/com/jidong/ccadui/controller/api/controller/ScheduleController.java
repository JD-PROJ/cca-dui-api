package com.jidong.ccadui.controller.api.controller;

import com.jidong.ccadui.controller.api.dto.ScheduleV1;
import com.jidong.ccadui.domain.calendar.service.Schedule;
import com.jidong.ccadui.domain.calendar.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    @NotNull
    private final ScheduleService scheduleService;

    @NotNull
    private final ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET,
                    value = "/schedules/{memberNo}",
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("회원별 일정 목록 조회")
    public List<ScheduleV1> getSchedulesByMemberNo(@PathVariable long memberNo) {
        List<Schedule> schedules = scheduleService.getSchedulesByMemberNo(memberNo);
        return schedules
                .stream()
                .map(a -> modelMapper.map(a, ScheduleV1.class))
                .collect(Collectors.toList());
    }
}
