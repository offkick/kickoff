package com.kickoff.api.controller.survey;

import com.kickoff.api.controller.survey.dto.CreateOptionGroupsRequest;
import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.api.service.survey.OptionGroupService;
import com.kickoff.api.service.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * (1) CSV 선수 데이터 찾기
 * (2) XLSX 파일 데이터를 일괄로 넣기 <-- 배치 POI(EXCEL 읽고 다운로드)
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyRequestService;
    private final OptionGroupService optionGroupService;

    @PostMapping("/{memberId}")
    public String survey(@PathVariable Long memberId, @RequestBody SurveyRequest request)
    {
        surveyRequestService.survey(memberId, request);
        return "SUCCESS";
    }

    @PostMapping("/option-groups")
    public void createOptionGroups(@RequestBody CreateOptionGroupsRequest createOptionGroupsRequest)
    {
        optionGroupService.create(createOptionGroupsRequest);
    }
}
