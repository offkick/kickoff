package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * optionChoiceName : 손흥민
 */
@Entity
@NoArgsConstructor
@Getter
public class OptionChoices extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionChoicesId;
    private String optionChoiceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="optionGroups_id")
    private OptionGroups optionGroups;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public OptionChoices(String optionChoiceName, OptionGroups optionGroups)
    {
        this.optionGroups = optionGroups;
        this.optionChoiceName = optionChoiceName;
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
