package com.jidong.ccadui.domain.calendar.service;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Schedule {
    private long scheduleNo;
    private String scheduleTitle;
    private long scheduleOwnerNo;
    private String scheduleStatus;
    private boolean isOwner;
    private Date beginDate;
    private Date endDate;
    private Date confirmDate;
    private long createNo;

    public Schedule(long scheduleNo,
                    String scheduleTitle,
                    long scheduleOwnerNo,
                    String scheduleStatus,
                    Date beginDate,
                    Date endDate,
                    Date confirmDate,
                    long createNo) {
        this.scheduleNo = scheduleNo;
        this.scheduleTitle = scheduleTitle;
        this.scheduleStatus = scheduleStatus;
        this.scheduleOwnerNo = scheduleOwnerNo;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.confirmDate = confirmDate;
        this.createNo = createNo;
    }

    public boolean setIsOwner() {
        if(scheduleOwnerNo == createNo) {
            isOwner = true;
        }
        return isOwner;
    }
}
