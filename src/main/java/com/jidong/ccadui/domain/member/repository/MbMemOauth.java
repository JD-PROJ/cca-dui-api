package com.jidong.ccadui.domain.member.repository;

import com.jidong.ccadui.domain.BaseTimeEntity;
import java.io.Serializable;
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
public class MbMemOauth extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = -7548773257794753692L;

    @Id
    @Column(name = "MEM_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memNo;

    @Column(name="SERVICE_NAME")
    private String serviceName;

    @Column(name="SERVICE_USER_ID")
    private String serviceUserId;

    @Column(name="SERVICE_USER_EMAIL")
    private String serviceUserEmail;

    @Column(name="SERVICE_PROFILE_ID")
    private String serviceProfileId;

    @Column(name="ACCESS_TOCKEN")
    private String accessToken;

    @Column(name = "UPDATE_NO")
    private String updateNo;

    @Builder
    public MbMemOauth(long memNo, String serviceName, String serviceUserId, String serviceProfileId, String accessToken) {
        this.memNo = memNo;
        this.serviceName = serviceName;
        this.serviceUserId = serviceUserId;
        this.serviceProfileId = serviceProfileId;
        this.accessToken = accessToken;
    }
}
