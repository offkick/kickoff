package com.kickoff.core.survey;

import com.kickoff.core.TestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class SurveyHeadersRepositoryTest {
    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;

    @Test
    @Commit
    public void save()
    {
        //give
        SurveyHeaders headers = SurveyHeaders.builder()
                .surveyName("프리미어리그")
                .instruction("3/31 토트넘 vs 루턴타운")
                .build();
        //when
        surveyHeadersRepository.save(headers);
        //then
        Assertions.assertThat(headers.getSurveyHeaderId()).isNotNull();
    }
}
