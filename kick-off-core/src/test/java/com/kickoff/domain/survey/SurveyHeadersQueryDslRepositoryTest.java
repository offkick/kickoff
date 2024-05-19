package com.kickoff.domain.survey;

import com.kickoff.domain.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class SurveyHeadersQueryDslRepositoryTest {

    @Autowired
    SurveyHeadersQueryDslRepository repository;

    @Test
    public void test()
    {

    }

}
