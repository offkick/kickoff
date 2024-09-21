package com.kickoff.core.config.security.oauth2;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRole;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String profile
) {
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes)
    {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributes);
            default -> throw new IllegalArgumentException("not supported provider");
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes)
    {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes)
    {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .profile((String) profile.get("profile_image_url"))
                .build();
    }

    public Member toEntity()
    {
        return Member.builder()
                .nickname(name)
                .email(email)
                .memberRoles(List.of(MemberRole.USER))
                .build();
    }
}