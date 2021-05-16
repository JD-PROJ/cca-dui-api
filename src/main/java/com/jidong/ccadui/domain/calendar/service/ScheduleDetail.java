package com.jidong.ccadui.domain.calendar.service;

import java.util.Date;
import lombok.Data;

@Data
public class ScheduleDetail {

    private long scheduleNo;
    private String scheduleTitle;
    private long scheduleOwnerNo;
    private String scheduleStatus;
    private boolean isOwner;
    private Date beginDate;
    private Date endDate;
    private Date confirmDate;
    private long createNo;
    private Date nonAvailableDate;

    public ScheduleDetail() {

    }

    public ScheduleDetail(long scheduleNo,
                          String scheduleTitle,
                          long scheduleOwnerNo,
                          String scheduleStatus,
                          Date beginDate,
                          Date endDate,
                          Date confirmDate,
                          long createNo,
                          Date nonAvailableDate) {
        this.scheduleNo = scheduleNo;
        this.scheduleTitle = scheduleTitle;
        this.scheduleStatus = scheduleStatus;
        this.scheduleOwnerNo = scheduleOwnerNo;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.confirmDate = confirmDate;
        this.createNo = createNo;
        this.nonAvailableDate = nonAvailableDate;
    }

    public boolean setIsOwner() {
        if (scheduleOwnerNo == createNo) {
            isOwner = true;
        }
        return isOwner;
    }
}
