package com.kickoff.api.service.survey;

import com.kickoff.api.controller.survey.dto.CreateOptionGroupsRequest;
import com.kickoff.domain.survey.OptionChoices;
import com.kickoff.domain.survey.OptionGroups;
import com.kickoff.domain.survey.OptionGroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class OptionGroupService {
    private final OptionGroupsRepository optionGroupsRepository;

    public void create(CreateOptionGroupsRequest createOptionGroupsRequest)
    {
        List<OptionChoices> optionChoicesList = createOptionGroupsRequest.getOptionChoiceName()
                .stream()
                .map(s-> OptionChoices.builder().build())
                .toList();

        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName(createOptionGroupsRequest.getOptionGroupName())
                .build();

        for (OptionChoices choices : optionChoicesList)
        {
            optionGroups.addOptionChoice(choices);
        }

        optionGroupsRepository.save(optionGroups);
    }
}
