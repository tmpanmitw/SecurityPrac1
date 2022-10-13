package com.sparta.securityprac1.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {

    }
    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    //JwtFilter 에서 SecurityContext 에 세팅한 유저 정보를 꺼냅니다.
    //저는 무조건 memberId 를 저장하게 했으므로 꺼내서 Long 타입으로 파싱하여 반환합니다.
    //SecurityContext 는 ThreadLocal 에 사용자의 정보를 저장합니다.
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}