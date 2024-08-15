package com.kickoff.api.controller.member;

import com.kickoff.api.controller.member.dto.MemberJoinRequest;
import com.kickoff.api.service.board.member.MemberCommandService;
import com.kickoff.api.service.board.member.MemberInfoResponse;
import com.kickoff.api.service.board.member.MemberQueryService;
import com.kickoff.api.service.board.member.dto.UpdateMemberServiceRequest;
import com.kickoff.core.config.security.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버 컨트롤러", description = "멤버 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/join")
    public Long join(@RequestBody MemberJoinRequest request)
    {
        return memberCommandService.join(request.toServiceDto());
    }

    @PutMapping("/info")
    public Long updateMember(@RequestBody UpdateMemberServiceRequest request)
    {
        return memberCommandService.updateMember(request);
    }

    @DeleteMapping
    public void deleteMember()
    {
        memberCommandService.deleteMember(AuthUtil.currentUserId());
    }

    @GetMapping("/info")
    public MemberInfoResponse findMemberInfo()
    {
        return memberQueryService.findMemberInfo(AuthUtil.currentUserId());
    }

    @PutMapping("/password")
    public void passwordChange(@RequestBody PasswordChangeRequest request)
    {
        memberCommandService.passwordChange(request.currentPassword(), request.newPassword());
    }
}
