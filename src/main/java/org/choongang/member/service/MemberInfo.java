package org.choongang.member.service;

import lombok.Builder;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Data
@Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String userId;
    private String password;
    private Member member;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    //인가에 관한 권한
        return null;
    }

    @Override
    public String getPassword() {//비번
        return null;
    }

    @Override
    public String getUsername() {//아이디
        return StringUtils.hasText(email) ? email : userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//비번이 만ㄽ
        return true;
    }

    @Override
    public boolean isEnabled() {//탈퇴
        return true;
    }
}
