package com.berkayg.manager;

import com.berkayg.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.berkayg.constant.EndPoints.SAVE;

/**
 * Microserviceler arasında iletişimi RestApi üzerinden sağlamak için kullanılacak interface'dir.
 * Burada iletişim kurulacak olan servisin, controller ortamına istek atacağız.
 * Burada 2 parametre kullanmak zorundayız.
 * 1- url: istek atacaüım servisin controller sınıfına erişim adresini yazacağız.
 * 2- name: zorunlu değildir ama aynı ismi taşıyan birden çok manager olursa hata verir. Ve bu sorunu bulmak kolay olmayabilir.
 * Yani name'i mutlaka unique vermelisiniz.
 */

@FeignClient(url = "http://localhost:9091/api/v1/user", name = "userManager")
public interface UserManager {
    @PostMapping(SAVE)
    ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto);

}