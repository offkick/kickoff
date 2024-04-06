package com.kickoff.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionsRepository extends JpaRepository<QuestionOptions, Long> {
    List<QuestionOptions> findByQuestions(Questions questions);

    @Query("select qo from QuestionOptions qo where qo.optionChoices.optionChoicesId = :optionChoicesId and qo.questions.questionId = :questionId")
    Optional<QuestionOptions> findByQuestionsIdAndAndOptionChoicesId(@Param("optionChoicesId") Long optionChoicesId, @Param("questionId") Long questionId);
}
