package com.kickoff.core.survey;

import com.kickoff.core.TestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class OptionChoicesRepositoryTest {
    @Autowired
    OptionGroupsRepository optionGroupsRepository;

    @Test
    public void save()
    {
        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName("공격수")
                .build();

        OptionChoices op1 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("손흥민")
                .build();

        OptionChoices op2 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("주민규")
                .build();

        OptionChoices op3 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("황희찬")
                .build();

        optionGroups.addOptionChoice(op1);
        optionGroups.addOptionChoice(op2);
        optionGroups.addOptionChoice(op3);
        optionGroupsRepository.save(optionGroups);

        Assertions.assertThat(optionGroups.getOptionChoicesList()).hasSize(3);
    }
}
