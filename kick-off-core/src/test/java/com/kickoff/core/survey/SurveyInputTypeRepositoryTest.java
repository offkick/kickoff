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
public class SurveyInputTypeRepositoryTest {
    @Autowired
    SurveyInputTypeRepository surveyInputTypeRepository;

    @Test
    public void save()
    {
        SurveyInputType surveyInputType = new SurveyInputType();
        surveyInputType.setInputTypeName("select");
        surveyInputTypeRepository.save(surveyInputType);
        Assertions.assertThat(surveyInputType.getSurveyInputTypeId()).isNotNull();


    }
}
