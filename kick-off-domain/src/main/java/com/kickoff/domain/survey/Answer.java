package com.kickoff.domain.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerText;
    private Long answerNumber;
    private boolean answerYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionOptions_id")
    private QuestionOptions questionOptions;

    private Long memberId;

    @Builder
    public Answer(Long answerId, String answerText, Long answerNumber, boolean answerYn, QuestionOptions questionOptions, Long memberId) {
        this.answerId = answerId;
        this.answerText = answerText;
        this.answerNumber = answerNumber;
        this.answerYn = answerYn;
        this.questionOptions = questionOptions;
        this.memberId = memberId;
    }
}
