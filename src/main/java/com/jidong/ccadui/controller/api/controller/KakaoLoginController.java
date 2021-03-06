package com.jidong.ccadui.controller.api.controller;

import com.jidong.ccadui.controller.api.dto.KakaoAPIResultVO;
import com.jidong.ccadui.controller.api.dto.KakaoLoginResultEnum;
import com.jidong.ccadui.controller.api.dto.KakaoLoginResultV1;
import com.jidong.ccadui.domain.login.service.KakaoLoginService;
import com.jidong.ccadui.domain.member.service.MemberOAuth;
import com.jidong.ccadui.domain.member.service.MemberService;
import com.jidong.ccadui.domain.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @NotNull
    private final JwtService jwtService;

    @NotNull
    private final MemberService memberService;

    @NotNull
    private final KakaoLoginService kakaoLoginService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/login/kakao", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("인증을 위한 JWT Token 생성")
    public KakaoLoginResultV1 kakaoLogin(@RequestParam String accessToken) {
        // accessToken으로 JWT token 생성 및 전달

        KakaoLoginResultV1 kakaoLoginResultV1 = new KakaoLoginResultV1(KakaoLoginResultEnum.INTERNAL_SERVER_ERROR);

        // kakao accessToken으로 카카오 api 호출
        KakaoAPIResultVO kakaoAPIResultVO = kakaoLoginService.callKakaoUserAPI(accessToken);

        if(KakaoLoginResultEnum.SUCCESS.equalsCode(kakaoAPIResultVO.getCode())) {

            JSONObject jsonObject = kakaoAPIResultVO.getResult();

            // 카카오 회원 고유 ID
            String kakaoUserId = jsonObject.optString("id");

            JSONObject kakaoAccount = jsonObject.optJSONObject("kakao_account");
            String nickname;

            // 카카오 회원 닉네임
            if (kakaoAccount != null) {
                nickname = kakaoAccount.optJSONObject("profile").optString("nickName");
            } else {
                nickname = "ccadui" + kakaoUserId;
            }

            // 3. 이미 회원인지 판별
            MemberOAuth member = memberService.getMemberInfoByServiceUserId(kakaoUserId);
            String jwt;

            try {
                if (member == null) {
                    MemberOAuth memberOAuth = new MemberOAuth();

                    memberOAuth.setServiceName("kakao");
                    memberOAuth.setServiceUserId(kakaoUserId);
                    memberOAuth.setServiceName(nickname);
                    memberOAuth.setUpdateDate(LocalDateTime.now());
                    memberOAuth.setCreateDate(LocalDateTime.now());

                    memberService.insertMember(memberOAuth);

                    // JWT token 생성 및 전달
                    jwt = createJwtToken(memberOAuth);

                } else {
                    // JWT token 생성 및 전달
                    jwt = createJwtToken(member);
                }
            } catch (Exception e) {
                log.error("JWT Token generate error : " + kakaoUserId);
                return new KakaoLoginResultV1(KakaoLoginResultEnum.JWT_TOKEN_GENERATE_ERROR);
            }

            return new KakaoLoginResultV1(KakaoLoginResultEnum.SUCCESS, jwt);

        } else {
            kakaoLoginResultV1.setResultCode(kakaoAPIResultVO.getCode());
            kakaoLoginResultV1.setResultMessage(kakaoAPIResultVO.getMessage());
            return kakaoLoginResultV1;
        }
    }

    private String createJwtToken(MemberOAuth member) throws Exception {
        String jwt;
        jwt = jwtService.create(
                "user", member, "user"
        );
        return jwt;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login/token")
    @ApiOperation("토큰 검증")
    public ResponseEntity<Object> vaildJwtToken(@RequestParam String accessToken) {
        log.info("POST : /api/loginToken");

        Object result = null;

        if (jwtService.validToken(accessToken, "user")) {
            result = jwtService.getTokenInfo(accessToken, "user");
        }

        log.info(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
