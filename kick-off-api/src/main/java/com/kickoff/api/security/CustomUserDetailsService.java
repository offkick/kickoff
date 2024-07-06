package com.kickoff.api.security;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        log.info("[START] - loadUserByUsername");
        Member member = memberRepository.getWithRoles(username);

        if(member == null)
        {
            throw new RuntimeException();
        }

        return new MemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.getNickName(),
                member.getMemberId().toString()
        );
    }
}
