package org.choongang.member.service;

import lombok.RequiredArgsConstructor;
import org.choongang.member.Authority;
import org.choongang.member.controllers.JoinValidator;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Authorities;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.AuthoritiesRepostory;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final MemberRepository memberRepository;
    private final AuthoritiesRepostory authoritiesRepostory;
    private final JoinValidator validator;
    private final PasswordEncoder encoder;

    public void process(RequestJoin form, Errors errors) {//커먼드 객체
        validator.validate(form,errors);
        if(errors.hasErrors()) {
            return;
        }
        //비밀번호 BCrypt로 해시화
        String hash = encoder.encode(form.getPassword());
        //Member member = new Member();
        //member.setEmail(form.getEmail());

        //Member member = new ModelMapper().map(form,Member.class);
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setPassword(hash);
        member.setUserId(form.getUserId());

        process(member);

        //회원가입시에는 일반 사용자 권한 부여 (USER)
        Authorities authorities = new Authorities();
        authorities.setMember(member);
        authorities.setAuthority(Authority.USER);
        authoritiesRepostory.saveAndFlush(authorities);
    }

    public void process(Member member) {
        memberRepository.saveAndFlush(member);
    }
}
