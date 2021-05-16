package com.jidong.ccadui.controller.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jidong.ccadui.domain.calendar.service.ScheduleDetail;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDetailV1 {
    @ApiModelProperty(notes = "스케쥴 번호")
    private long scheduleNo;

    @ApiModelProperty(notes = "스케쥴 이름")
    private String scheduleTitle;

    @ApiModelProperty(notes = "시작일자")
    private Date beginDate;

    @ApiModelProperty(notes = "종료일자")
    private Date endDate;

    @ApiModelProperty(notes = "스케쥴 진행 상태 > 01:진행중, 02:취소, 03:확정")
    private String status;

    @ApiModelProperty(notes = "스케쥴 생성자 여부")
    private boolean isOwner;

    @ApiModelProperty(notes = "확정일자")
    private Date confirmDate;

    @ApiModelProperty(notes = "불가능한 일자")
    private List<Date> nonAvailableDates;

    public ScheduleDetailV1() {

    }

    public ScheduleDetailV1(List<ScheduleDetail> scheduleDetails) {
        ScheduleDetail detail = scheduleDetails.get(0);

        this.scheduleNo = detail.getScheduleNo();
        this.scheduleTitle = detail.getScheduleTitle();
        this.status = detail.getScheduleStatus();
        this.isOwner = detail.isOwner();
        this.beginDate = detail.getBeginDate();
        this.endDate = detail.getEndDate();
        this.confirmDate = detail.getConfirmDate();

        List<Date> nonAvailableDate = new ArrayList<>();
        for (ScheduleDetail d : scheduleDetails) {
            nonAvailableDate.add(d.getNonAvailableDate());
            this.nonAvailableDates = nonAvailableDate;
        }
    }
}
