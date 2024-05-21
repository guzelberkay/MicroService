package com.berkayg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USERNAME_ALREADY_TAKEN(1001,
            "Bu username daha önce kullanılmış. Yeniden deneyiniz.",
            HttpStatus.BAD_REQUEST),
    USERNAME_OR_PASSWORD_WRONG(1002,
            "Kullanıcı adı veya parola yanlış.",
            HttpStatus.BAD_REQUEST),
    PASSWORDS_NOT_MATCHED(1003,
            "Girdiğiniz parolalar uyuşmamaktadır. Lütfen kontrol ediniz.",
            HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2001,
            "Token geçersizdir.",
            HttpStatus.BAD_REQUEST),
    TOKEN_CREATION_FAILED(2002,
            "Token yaratmada hata meydana geldi.",
            HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_VERIFY_FAILED(2003,
            "Token verify etmede bir hata meydana geldi.",
            HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_ARGUMENT_NOTVALID(2003,
            "Token argümanı yanlış formatta.",
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