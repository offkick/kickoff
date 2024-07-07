package com.kickoff.api.service;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import com.kickoff.global.config.security.MemberDTO;
import com.kickoff.global.config.security.TokenProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class InitService {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init()
    {
        String master = encoder.encode("master");

        Member member = Member.builder().email("master@master.com")
                .nickName("master")
                .password(master)
                .build();

        memberRepository.save(member);

        MemberDTO dto = new MemberDTO(
                "master@master.com",
                "master",
                "master",
                "1",
                Arrays.asList()
        );
        String token = tokenProvider.generateAccessToken(dto);
        log.info("[generated master token] : Bearer {}", token);
    }


}
