package com.kickoff.core.config.security.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private String picture;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes)
    {
        return switch (provider) {
            case "google" -> ofGoogle(attributeKey, attributes);
            default -> throw new IllegalArgumentException("지원 하지 않는 provider");
        };
    }

    private static OAuth2Attribute ofGoogle(String attributeKey, Map<String, Object> attributes)
    {
        return OAuth2Attribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    Map<String, Object> convertToMap()
    {
        return Map.of(
                "id", attributeKey,
                "key", attributeKey,
                "name", name,
                "email", email,
                "picture", picture
        );
    }
}