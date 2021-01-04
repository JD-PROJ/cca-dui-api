package com.jidong.ccadui.domain.member.repository;

import static javax.persistence.GenerationType.IDENTITY;

import com.jidong.ccadui.domain.BaseTimeEntity;
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
public class MbMemOauth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MEM_NO")
    private Long memNo;

    @Column(name="SVC_NM")
    private String svcNm;

    @Column(name="SVC_USER_ID")
    private String svcUsrId;

    @Builder
    public MbMemOauth(long memNo, String svcNm, String svcUsrId) {
        this.memNo = memNo;
        this.svcNm = svcNm;
        this.svcUsrId = svcUsrId;
    }
}
