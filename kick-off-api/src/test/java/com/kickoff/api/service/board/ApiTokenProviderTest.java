package com.kickoff.api.service.board;

import com.kickoff.KickOffApiApplication;
import com.kickoff.core.member.MemberRepository;
import com.kickoff.api.config.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest(classes = KickOffApiApplication.class)
class ApiTokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder encoder;
}