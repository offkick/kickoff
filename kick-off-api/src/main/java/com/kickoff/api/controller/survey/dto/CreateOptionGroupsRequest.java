package com.kickoff.api.controller.survey.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CreateOptionGroupsRequest {
    private String optionGroupName;
    private List<String> optionChoiceName;
}
