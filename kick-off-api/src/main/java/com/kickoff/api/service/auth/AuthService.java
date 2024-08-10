package com.kickoff.api.service.auth;

import com.kickoff.core.config.security.MemberDTO;
import com.kickoff.core.config.security.TokenProvider;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthenticationResponse authentication(AuthenticationRequest request)
    {
        Member member = memberService.findByEmail(request.email()).orElseThrow();

        if(!member.matchPassword(request.password(), passwordEncoder))
        {
            throw new IllegalArgumentException("Password 틀림");
        }

        MemberDTO dto = MemberDTO.toDTO(member);
        String accessToken = tokenProvider.generateAccessToken(dto);
        String refreshToken = tokenProvider.generateRefreshToken(dto);
        return new AuthenticationResponse(accessToken, refreshToken, member.getEmail());
    }

    public void authentication(String jwtToken)
    {
        Map<String, Object> stringObjectMap = tokenProvider.validateToken(jwtToken);

        if (stringObjectMap == null)
        {
            throw new IllegalArgumentException("Invalid Jwt Token");
        }
    }
}
