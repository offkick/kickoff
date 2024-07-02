package com.kickoff.core.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String nickName;
    private String password;

    @Builder
    public Member(Long memberId, String email, String nickName, String password) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public void update(Member updateMember)
    {
        setNickName(updateMember.getNickName());
        setPassword(updateMember.getPassword());
    }

    private void setEmail(String email)
    {
        if(email == null || email.isBlank())
        {
            return;
        }
        this.email = email;
    }

    private void setNickName(String nickName)
    {
        if(nickName == null || nickName.isBlank())
        {
            return;
        }
        this.nickName = nickName;
    }

    private void setPassword(String password)
    {
        if(password == null || password.isBlank())
        {
            throw new IllegalArgumentException("비밀번호 입력해주세요");
        }
        this.password = password;
    }
}
