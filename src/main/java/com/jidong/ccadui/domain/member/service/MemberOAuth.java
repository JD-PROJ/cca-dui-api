package com.jidong.ccadui.domain.member.service;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberOAuth {
    private long memberNo;
    private String serviceName;
    private String serviceUserId;
    private String serviceUserName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public MemberOAuth(long memberNo, String serviceName, String serviceUserId, String serviceUserName,
                       LocalDateTime updateDate, LocalDateTime createDate) {
        this.memberNo = memberNo;
        this.serviceName = serviceName;
        this.serviceUserId = serviceUserId;
        this.serviceUserName = serviceUserName;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }
}
