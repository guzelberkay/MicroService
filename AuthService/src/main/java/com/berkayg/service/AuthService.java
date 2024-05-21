package com.berkayg.service;

import com.berkayg.dto.request.LoginRequestDto;
import com.berkayg.dto.request.RegisterRequestDto;
import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.entity.Auth;
import com.berkayg.exception.AuthServiceException;
import com.berkayg.exception.ErrorType;
//import com.berkayg.manager.UserProfileManager;
import com.berkayg.manager.UserManager;
import com.berkayg.mapper.AuthMapper;
import com.berkayg.rabbitmq.model.SaveAuthModel;
import com.berkayg.rabbitmq.producer.CreateUserProducer;
import com.berkayg.repository.AuthRepository;
import com.berkayg.utility.JwtTokenManager;
import com.berkayg.utility.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final TokenManager tokenManager;
    private final JwtTokenManager jwtTokenManager;
    private final UserManager userManager;
    private final CreateUserProducer createUserProducer;



    public Auth save(RegisterRequestDto dto) {
        //password ve repassword eşitliği kontrol edilir:
        if (!dto.getPassword().equals(dto.getRepassword())) {
            throw new AuthServiceException(ErrorType.PASSWORDS_NOT_MATCHED);
        }
        //username daha önce alınmış mı kontrol edilir:
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }
        //Kontrollerden başarılı bir şekilde geçildiyse dto'dan gelen bilgilerle Auth nesnesi oluşturulur.
        Auth auth = AuthMapper.INSTANCE.toAuth(dto);
        auth = authRepository.save(auth);
        //Bu auth nesnesi repository save metodu ile kaydedildikten sonra UserProfileService'e Profil oluşturmak için
        // gerekli bilgileri(userProfileSaveRequestDto) iletmemiz gerekir. OpenFegn ile gönderme
//        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
//                .authId(auth.getId())
//                .email(auth.getEmail())
//                .username(auth.getUsername())
//                .build();
//      userManager.save(userSaveRequestDto);
          //rabbirMQ
        createUserProducer.convertAndSend(SaveAuthModel.builder()
                .authId(auth.getId())
                .email(auth.getEmail())
                .username(auth.getUsername())
                .build());
        return auth;
    }

    /**
     * Username ve password ile doğrulama işlemi yapar. Eğer doğrulama başarısız olursa hata fırlatalım. Eğer doğrulama
     * başarılı ise bir kimlik verelim.
     *
     * @param dto
     * @return
     */
    public String doLogin(LoginRequestDto dto) {
        Auth auth = findOptionalByUsernameAndPassword(dto.getUsername(),
                dto.getPassword());
//		return tokenManager.createToken(auth.getId());
        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public Auth findOptionalByUsernameAndPassword(String username, String password) {
        return authRepository.findOptionalByUsernameAndPassword(username,
                        password)
                .orElseThrow(() -> new AuthServiceException(ErrorType.USERNAME_OR_PASSWORD_WRONG));

    }


    public List<Auth> findAll(String token) {
        Long idFromToken = null;

//			idFromToken = tokenManager.getIdFromToken(token);
        idFromToken =
                jwtTokenManager.getIdFromToken(token)
                        .orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));

        authRepository.findById(idFromToken).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();

    }
}