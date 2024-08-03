package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(name = "question_options_idx", unique = true, columnList = "questions_id, option_choices_id"))
public class QuestionOptions extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_choices_id")
    private OptionChoices optionChoices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id")
    private Questions questions;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public QuestionOptions(Long questionOptionId, OptionChoices optionChoices, Questions questions) {
        this.questionOptionId = questionOptionId;
        this.optionChoices = optionChoices;
        this.questions = questions;
        isDeleted = Boolean.FALSE;
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}
