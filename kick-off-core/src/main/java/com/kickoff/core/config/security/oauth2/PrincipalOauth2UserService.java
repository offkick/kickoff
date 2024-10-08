package com.kickoff.core.config.security.oauth2;

import com.kickoff.core.config.security.PrincipalDetails;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
    {
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        log.info("tr = " + oAuth2UserAttributes);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        log.info("registrationId = " + registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        log.info("userNameAttributeName = " + userNameAttributeName);
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);
        log.info("oAuth2UserInfo = " + oAuth2UserInfo);

        return new PrincipalDetails(getOrSave(oAuth2UserInfo), oAuth2UserAttributes, userNameAttributeName);
    }

    @Transactional
    public Member getOrSave(OAuth2UserInfo oAuth2UserInfo)
    {
        Member member = memberRepository.findByEmail(oAuth2UserInfo.email())
                .orElseGet(oAuth2UserInfo::toEntity);
        return memberRepository.save(member);
    }
}