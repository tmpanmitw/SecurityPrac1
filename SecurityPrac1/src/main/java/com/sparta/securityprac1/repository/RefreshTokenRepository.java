package com.sparta.securityprac1.repository;

import com.sparta.securityprac1.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Member ID 값으로 토큰을 가져오기 위해 findByKey 만 추가했습니다.
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}