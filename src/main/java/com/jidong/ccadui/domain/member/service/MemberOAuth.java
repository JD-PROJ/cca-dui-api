package com.jidong.ccadui.domain.member.service;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberOAuth {
    private long memberNo;
    private String serviceName;
    private String serviceUserId; // 서비스 고유 아이디
    private String serviceProfileId; // 서비스 닉네임
    private String accessToken;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public MemberOAuth(long memberNo, String serviceName, String serviceUserId, String serviceProfileId, String accessToken,
                       LocalDateTime updateDate, LocalDateTime createDate) {
        this.memberNo = memberNo;
        this.serviceName = serviceName;
        this.serviceUserId = serviceUserId;
        this.serviceProfileId = serviceProfileId;
        this.accessToken = accessToken;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }
}
