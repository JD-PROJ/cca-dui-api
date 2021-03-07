package com.jidong.ccadui.domain.calendar.repository;

import com.jidong.ccadui.domain.BaseTimeEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CalSchedule extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "SCHEDULE_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleNo;

    @Column(name = "SCHEDULE_TITLE")
    private String scheduleTitle;

    @Column(name = "SCHEDULE_STATUS")
    private String scheduleStatus;

    @Column(name = "SCHEDULE_OWNER_NO")
    private long scheduleOwnerNo;

    @Column(name = "BEGIN_DATE")
    private Date beginDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CONFIRM_DATE")
    private Date confirmDate;

    @Column(name = "UPDATE_NO")
    private String updateNo;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "CREATE_NO")
    private long createNo;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Builder
    public CalSchedule(long scheduleNo,
                       String scheduleTitle,
                       String scheduleStatus,
                       long scheduleOwnerNo,
                       Date beginDate,
                       Date endDate,
                       Date confirmDate,
                       long createNo) {
        this.scheduleNo = scheduleNo;
        this.scheduleTitle = scheduleTitle;
        this.scheduleOwnerNo = scheduleOwnerNo;
        this.scheduleStatus = scheduleStatus;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.confirmDate = confirmDate;
        this.createNo = createNo;
    }
}
