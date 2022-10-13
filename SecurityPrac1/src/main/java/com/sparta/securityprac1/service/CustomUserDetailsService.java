package com.sparta.securityprac1.service;

import com.sparta.securityprac1.entity.Member;
import com.sparta.securityprac1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
//UserDetailsService 인터페이스를 구현한 클래스입니다.
//loadUserByUsername 메소드를 오버라이드 하는데 여기서 넘겨받은 UserDetails 와 Authentication 의 패스워드를 비교하고 검증하는 로직을 처리합니다.
//물론 DB 에서 username 을 기반으로 값을 가져오기 때문에 아이디 존재 여부도 자동으로 검증 됩니다.
//loadUserByUsername 메소드를 어디서 호출하는지 내부를 타고 들어가봅니다.

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}