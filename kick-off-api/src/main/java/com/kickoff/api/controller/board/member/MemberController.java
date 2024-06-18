package com.kickoff.api.controller.board.member;

import com.kickoff.api.service.board.member.ApiMemberService;
import com.kickoff.api.service.board.member.dto.CreateMemberServiceRequest;
import com.kickoff.api.service.board.member.dto.UpdateMemberServiceRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버 컨트롤러", description = "멤버 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final ApiMemberService apiMemberService;

    @PostMapping
    public Long createMember(@RequestBody CreateMemberServiceRequest request)
    {
        return apiMemberService.createMember(request);
    }

    @PutMapping("/{memberId}")
    public Long updateMember(
            @PathVariable(value = "memberId") Long memberId,
            @RequestBody UpdateMemberServiceRequest request)
    {
        return apiMemberService.updateMember(memberId,request);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable(value = "memberId") Long memberId)
    {
        apiMemberService.deleteMember(memberId);
    }
}
