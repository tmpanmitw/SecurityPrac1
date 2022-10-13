package com.sparta.securityprac1.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Table;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

//key 에는 Member ID 값이 들어갑니다.
//value 에는 Refresh Token String 이 들어갑니다.
//위에서 언급한대로 RDB 로 구현하게 된다면 생성/수정 시간 컬럼을 추가하여 배치 작업으로 만료된 토큰들을 삭제해주어야 합니다.
@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}