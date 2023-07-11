package com.v3.furry_frined_chat_cud.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {

    private Long memberId;
    private String memberName;
}
