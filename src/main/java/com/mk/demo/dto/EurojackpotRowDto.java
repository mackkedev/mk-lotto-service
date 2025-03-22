package com.mk.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EurojackpotRowDto {

    private List<Integer> mainNumbers;  // 5 main numbers (1-50)
    private List<Integer> euroNumbers;  // 2 Euro numbers (1-12)

    public EurojackpotRowDto(List<Integer> mainNumbers, List<Integer> euroNumbers) {
        if (mainNumbers.size() != 5 || euroNumbers.size() != 2) {
            throw new IllegalArgumentException("Eurojackpot row must have 5 main numbers and 2 Euro numbers.");
        }
        this.mainNumbers = mainNumbers;
        this.euroNumbers = euroNumbers;
    }

    public List<Integer> getMainNumbers() {  // This method must exist!
        return mainNumbers;
    }

    public List<Integer> getEuroNumbers() {  // This method must exist!
        return euroNumbers;
    }
}
