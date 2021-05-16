package com.jidong.ccadui.domain.calendar.repository;

import com.jidong.ccadui.domain.BaseTimeEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CalScheduleMem extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "MEM_NO")
    private long memberNo;

    @Id
    @Column(name = "SCHEDULE_NO")
    private long scheduleNo;

    @Id
    @Column(name = "NON_AVAILABLE_TIME")
    private Date nonAvailableTime;

    @Column(name = "UPDATE_NO")
    private long updateNo;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "CREATE_NO")
    private long createNo;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

//    @Builder
//    public CalScheduleMem(long memberNo,
//                        long scheduleNo,
//                       Date nonAvailableTime,
//                       long updateNo,
//                       Date updateDate,
//                       long createNo,
//                       Date createDate) {
//        this.memberNo = memberNo;
//        this.scheduleNo = scheduleNo;
//        this.createDate = createDate;
//    }
}
