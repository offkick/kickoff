package com.kickoff.api.controller.survey;

import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.api.controller.survey.dto.SurveyResponse;
import com.kickoff.api.service.survey.SurveyRequestService;
import com.kickoff.domain.survey.dto.SurveyHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/survey")
public class SurveyController {
    private final SurveyRequestService surveyRequestService;

    @PostMapping("/{memberId}")
    public String survey(@PathVariable Long memberId, @RequestBody SurveyRequest request)
    {
        surveyRequestService.survey(memberId, request);
        return "SUCCESS";
    }

    @GetMapping("/{surveyHeadersId}")
    public SurveyResponse findById(@PathVariable Long surveyHeadersId)
    {
        SurveyHeaderDTO dto = surveyRequestService.findById(surveyHeadersId);
        return SurveyResponse.of(dto);
    }
}
