package com.goufu.springboot_demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goufu.springboot_demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String getToken(User user) {
        //持续时间
        long expireHour = 1;
        String token;
        Date date = new Date(System.currentTimeMillis() + expireHour * 1000 * 3600);
        token = JWT.create().withAudience(user.getUserId())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
