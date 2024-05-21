package com.berkayg.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. Token üretmeli
    public String createToken(Long id) {
        return "authtoken:" +id;
    }
    //2. Ürettiğimiz token'ın bilgi çıkarımı yapmalı:
    // authtoken:2453 buradan geriye 2453 değeri dönmeli
    public Long getIdFromToken(String token) {
        String[] split = token.split("authtoken:");
        return Long.parseLong(split[1]);
    }
}
