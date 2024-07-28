package com.kickoff.core.config.security;

import com.kickoff.core.member.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
public class MemberDTO extends User {
    @Getter
    private String email;

    @Getter
    private String memberId;
    private String password;

    private String nickName;
    private List<String> roleNames = new ArrayList<>();

    public String getUsername()
    {
        return email;
    }

    public MemberDTO(
            String email,
            String password,
            String nickName,
            String memberId,
            List<String> roleNames)
    {
        super(
                email,
                password,
                roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList())
        );
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.memberId = memberId;
    }

    public Map<String, Object> getClaims()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("password", password);
        dataMap.put("nickName", nickName);
        dataMap.put("roleNames", roleNames);
        dataMap.put("memberId", memberId);
        return dataMap;
    }

    public static MemberDTO toDTO(Member member)
    {
        return new MemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.getNickName(),
                member.getMemberId().toString(),
                member.getMemberRoles()
                        .stream()
                        .map(str -> "ROLE_" + str)
                        .collect(Collectors.toList())
        );
    }
}
