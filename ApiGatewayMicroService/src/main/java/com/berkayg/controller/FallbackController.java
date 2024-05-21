package com.berkayg.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackAuth() {
        //return ResponseEntity.internalServerError().body("Auth service şu an hizmet verememektedir.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Auth service şu an hizmet verememektedir");
    }
    @GetMapping("/user")
    public ResponseEntity<String> getFallbackUser() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User service şu an hizmet verememektedir");
    }
}