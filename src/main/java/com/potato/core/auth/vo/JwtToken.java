package com.potato.core.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

	private String grantType;
    private String accessToken;
    private String refreshToken;

}
