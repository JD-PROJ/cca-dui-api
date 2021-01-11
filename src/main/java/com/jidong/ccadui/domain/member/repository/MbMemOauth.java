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

    @Column(name="SVC_NM")
    private String svcNm;

    @Column(name="SVC_USER_ID")
    private String svcUsrId;

    @Column(name="SVC_USER_NM")
    private String svcUsrNm;

    @Builder
    public MbMemOauth(long memNo, String svcNm, String svcUsrId, String svcUsrNm) {
        this.memNo = memNo;
        this.svcNm = svcNm;
        this.svcUsrId = svcUsrId;
        this.svcUsrNm = svcUsrNm;
    }
}
