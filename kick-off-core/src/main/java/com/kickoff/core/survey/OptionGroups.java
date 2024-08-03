package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * optionGroupName : 공격수
 */
@Entity
@NoArgsConstructor
@Getter
public class OptionGroups extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionGroupId;

    private String optionGroupName;

    @OneToMany(mappedBy = "optionGroups", cascade = CascadeType.ALL)
    private List<OptionChoices> optionChoicesList = new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void addOptionChoice(OptionChoices optionChoices) {
        if (this.optionChoicesList == null) {
            this.optionChoicesList = new ArrayList<>();
        }

        this.getOptionChoicesList().add(optionChoices);
    }

    @Builder
    public OptionGroups(
            Long optionGroupId,
            String optionGroupName
    ) {
        this.optionGroupId = optionGroupId;
        this.optionGroupName = optionGroupName;
        isDeleted = Boolean.FALSE;
    }
}
