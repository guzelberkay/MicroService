package com.berkayg.controller;

import com.berkayg.constant.EndPoints;

import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.dto.request.UserUpdateRequestDto;
import com.berkayg.entity.User;
import com.berkayg.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berkayg.constant.EndPoints.*;
import static com.berkayg.constant.EndPoints.MESSAGE;

/**
 * http://localhost:9091/api/v1/user/save
 */

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto) {
        service.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserUpdateRequestDto dto) {
        service.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(MESSAGE)
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("User Servise istek geldi..");
    }
    @GetMapping("/getupper")
    public ResponseEntity<String> getUpper(String name){
        return ResponseEntity.ok(service.getUpper(name));
    }
}