package com.berkayg.service;


import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.dto.request.UserUpdateRequestDto;
import com.berkayg.entity.User;
import com.berkayg.exception.ErrorType;
import com.berkayg.exception.UserServiceException;
import com.berkayg.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void save(UserSaveRequestDto dto) {
        repository.save(User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .authId(dto.getAuthId())
                .build());
    }

    public void update(UserUpdateRequestDto dto) {
        /*
        Güncellem işleminde öncelikle guncellenecek kayıt bulunmalıdır.
        Kaydı getirmek için dto içinde gelen id bilgisini repository'e parametre olarak geçtik.
        Ardından bu id ye sahip

         */
        User user = repository.findById(dto.getId())
                .orElseThrow(() -> new UserServiceException(ErrorType.INVALID_USER_ID));

        user.setAbout(dto.getAbout());
        user.setPhoto(dto.getPhoto());
        user.setWebsite(dto.getWebsite());
        user.setPhone(dto.getPhone());
        // gerekli set ilemleri tamamladıktan sonra repository save methodu ile güncelleme işlemini gerçekleştiriyoruz.
        // save methodu eğer içinde id varsa güncellemei yoksa ekleme işlemi yapar.
        repository.save(user);
    }
    /**
     * Bu metod ile Thread sadece uzun süreli bir işlem simule etmektedir.
     *  Bu metod her zaman aynı girdi için aynı çıktıyı üretir.
     * kalem -> KALEM, şişe->ŞİŞE
     * Eğer bir metod ile cacheleme yapılmak isteniyorsa @Cacheable anotasyonu ile işaretlenmelidir.
     */
    @Cacheable(value = "getUpper")
    public String getUpper(String name){
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  name.toUpperCase();
    }
}