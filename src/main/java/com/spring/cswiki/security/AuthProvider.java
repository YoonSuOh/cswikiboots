/*package com.spring.cswiki.security;

import com.spring.cswiki.domain.Member;
import com.spring.cswiki.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private MemberService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = (String) authentication.getPrincipal(); // 로그인 창에 입력한 id
        String pwd = (String) authentication.getCredentials(); // 로그인 창에 입력한 password

        PasswordEncoder passwordEncoder = service.passwordEncoder();
        UsernamePasswordAuthenticationToken token;
        Member member = service.getUserByname(name);

        if (member != null && passwordEncoder.matches(pwd, member.getPwd())) { // 일치하는 user 정보가 있는지 확인
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("USER")); // 권한 부여

            token = new UsernamePasswordAuthenticationToken(member.getU_id(), null, roles);
            // 인증된 user 정보를 담아 SecurityContextHolder에 저장되는 token

            return token;
        }

        throw new BadCredentialsException("No such user or wrong password.");
        // Exception을 던지지 않고 다른 값을 반환하면 authenticate() 메서드는 정상적으로 실행된 것이므로 인증되지 않았다면 Exception을 throw 해야 한다.
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}*/
