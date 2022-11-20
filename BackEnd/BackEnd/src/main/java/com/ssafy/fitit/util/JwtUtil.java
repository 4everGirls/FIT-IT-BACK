package com.ssafy.fitit.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JwtUtil {
    private static final String SALT = "FITIT";

    //토큰 생성
    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        //유효기간도 설정해서 해볼것
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim(claimId, data)
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }
    //유효성 검사
    public void valid(String token) throws Exception {
        Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
    }
}
