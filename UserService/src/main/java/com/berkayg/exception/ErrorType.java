package com.berkayg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INVALID_USER_ID(2001,
            "Geçersiz Kullanıcı Id",
            HttpStatus.NOT_FOUND),

    USERNAME_ALREADY_TAKEN(1001,
            "Bu username daha önce kullanılmış. Yeniden deneyiniz.",
            HttpStatus.BAD_REQUEST),

    URUN_NOT_FOUND(5003,
            "Böyle bir Ürün bulunamadı.",
            HttpStatus.NOT_FOUND),

    MUSTERI_NOT_FOUND(5004,
            "Böyle bir müşteri bulunamadı.",
            HttpStatus.NOT_FOUND);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;

}