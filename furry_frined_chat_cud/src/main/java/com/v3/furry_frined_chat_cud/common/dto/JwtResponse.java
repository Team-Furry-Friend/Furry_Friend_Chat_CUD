package com.v3.furry_frined_chat_cud.common.dto;

import lombok.Getter;

@Getter
public class JwtResponse {

    private MemberData data;

    @Getter
    public static class MemberData {
        private Long memberId;
    }
}
