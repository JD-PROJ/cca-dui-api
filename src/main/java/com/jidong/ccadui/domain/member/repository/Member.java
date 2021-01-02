package com.jidong.ccadui.domain.member.repository;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long memNo;

    @Column
    private String serviceName;

    @Column(nullable = false)
    private String serviceUserId;

    @Builder
    public Member(long memNo, String serviceName, String serviceUserId) {
        this.memNo = memNo;
        this.serviceName = serviceName;
        this.serviceUserId = serviceUserId;
    }
}
