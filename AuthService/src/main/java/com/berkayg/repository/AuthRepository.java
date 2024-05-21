package com.berkayg.repository;

import com.berkayg.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    /**
     * Username daha önce alınmış mı kontrol sağlamak için kullanılır.
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
    /**
     * username ve password bilgilerini kontrol eder.
     */
    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
}
