package com.jidong.ccadui.domain.member.service;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class MemberOAuth {
    private long memberNo;
    private String serviceName;
    private String serviceUserId;
    private Date createDate;
    private Date updateDate;

    public MemberOAuth(long memberNo, String serviceName, String serviceUserId) {
        this.memberNo = memberNo;
        this.serviceName = serviceName;
        this.serviceUserId = serviceUserId;
    }
}
