package com.berkayg.controller;

import com.berkayg.constant.EndPoints;
import com.berkayg.dto.request.LoginRequestDto;
import com.berkayg.dto.request.RegisterRequestDto;
import com.berkayg.dto.response.RegisterResponseDto;
import com.berkayg.entity.Auth;
import com.berkayg.mapper.AuthMapper;
import com.berkayg.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.berkayg.constant.EndPoints.MESSAGE;


@RestController
@RequestMapping(EndPoints.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Register İşlemleri:
     */
    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {
        return ResponseEntity.ok(AuthMapper.INSTANCE.authToDto(authService.save(dto)));
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<String> dologin(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }
    /**
     * ApiGateway Deneme Metodu:
     */
    @GetMapping(MESSAGE)
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Auth Servise istek geldi..");
    }
}