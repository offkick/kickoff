package com.kickoff.domain.survey;

import com.kickoff.domain.TestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class OptionGroupsRepositoryTest {

    @Autowired
    OptionGroupsRepository optionGroupsRepository;

    @Test
    public void save()
    {
        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName("공격수")
                .build();
        optionGroupsRepository.save(optionGroups);

        Assertions.assertThat(optionGroups.getOptionGroupId()).isNotNull();
    }
}
