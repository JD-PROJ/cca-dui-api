package com.jidong.ccadui.controller.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleV1 {
    @ApiModelProperty(notes = "스케쥴 번호")
    private long scheduleNo;

    @ApiModelProperty(notes = "스케쥴 이름")
    private String scheduleTitle;

    @ApiModelProperty(notes = "스케쥴 진행 상태 > 01:진행중, 02:취소, 03:확정")
    private String status;

    @ApiModelProperty(notes = "스케쥴 생성자 여부")
    private boolean isOwner;

    @ApiModelProperty(notes = "시작일자")
    private Date beginDate;

    @ApiModelProperty(notes = "종료일자")
    private Date endDate;

    @ApiModelProperty(notes = "확정일자")
    private Date confirmDate;

}
