package com.jidong.ccadui.controller.api;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import com.jidong.ccadui.domain.member.service.MemberService;
import com.jidong.ccadui.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KakaoLoginController {
    // 카카오 인증 및 유효성 검증 Controller

    @Autowired
    private KakaoAPI kakaoAPI;

    @Autowired
    private JwtService jwtService;

    @NotNull
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/kakao/login",  produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation("카카오 로그인")
    public String kakaoLogin(@RequestParam String accessToken) {
        // accessToken으로 JWT token 생성 및 전달

        // 1. 토큰이 있으면 kakao User Info 를 가지고 옴.
        JSONObject jsonObject;
        if(!StringUtils.isEmpty(accessToken)) {
           jsonObject = kakaoAPI.getKaKaoUserInfo(accessToken);
        } else {
            return "";
        }

        //TODO : 회원번호는 다른 유니크한걸로 변경
        long memberNo = jsonObject.optLong("id");

        JSONObject kakaoAccount = jsonObject.optJSONObject("kakao_account");
        String nickname = "";
        if (kakaoAccount != null) {
            nickname = kakaoAccount.optJSONObject("profile").optString("nickName");
        } else {
            nickname = "ccadui" + memberNo;
        }

        //   2. 이미 회원인지 판별
        MemberOAuth member = memberService.getMemberInfo(memberNo);
        String jwt;
        if (member == null) {
            MemberOAuth memberOAuth = new MemberOAuth();

            memberOAuth.setMemberNo(memberNo);
            memberOAuth.setServiceName("kakao");
            memberOAuth.setServiceUserId(nickname);

            memberService.insertMember(memberOAuth);

            // JWT token 생성 및 전달
            jwt = jwtService.create(
                    "user", memberOAuth, "user"
            );

        } else {
            // JWT token 생성 및 전달
            jwt = jwtService.create(
                    "user", member, "user"
            );
        }

        return jwt;
    }

    @PostMapping("/login/token")
    @ApiOperation("토큰 검증")
    public Object token(@RequestParam String access_token) {
        log.info("POST : /api/loginToken");

        Object result = null;

        if (jwtService.isUsable(access_token)) {
            result = jwtService.get("user");
        }

        log.info(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
