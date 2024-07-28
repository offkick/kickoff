package com.kickoff.core.member;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;
    private String nickName;
    private String password;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_roles_mapping", joinColumns = @JoinColumn(name = "member_id"))
    private List<MemberRole> memberRoles = new ArrayList<>();

    @Builder
    public Member(Long memberId, String email, String nickName, String password, List<MemberRole> memberRoles) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.memberRoles = memberRoles;
    }

    public void update(Member updateMember)
    {
        setNickName(updateMember.getNickName());
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

    public boolean matchPassword(String pw, PasswordEncoder passwordEncoder)
    {
        return passwordEncoder.matches(pw, this.password);
    }

    public void updatePassword(String newPassword)
    {
        this.password = newPassword;
    }
}
