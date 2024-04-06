package com.kickoff.api.controller.survey;

import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.api.service.survey.SurveyRequestService;
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
}
