package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerText;
    private Long answerNumber;
    private boolean answerYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_options_id")
    private QuestionOptions questionOptions;

    private Long memberId;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public Answer(
            Long answerId,
            String answerText,
            Long answerNumber,
            boolean answerYn,
            QuestionOptions questionOptions,
            Long memberId) {
        this.answerId = answerId;
        this.answerText = answerText;
        this.answerNumber = answerNumber;
        this.answerYn = answerYn;
        this.questionOptions = questionOptions;
        this.memberId = memberId;
        isDeleted = Boolean.FALSE;

        if (answerYn && answerNumber == null && answerText == null)
        {
            throw new IllegalArgumentException("Create answer entity Error");
        }
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}
