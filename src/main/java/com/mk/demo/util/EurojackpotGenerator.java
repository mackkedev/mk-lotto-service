package com.mk.demo.util;

import com.mk.demo.dto.EurojackpotRowDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EurojackpotGenerator {

    private static final int MAIN_NUMBERS_COUNT = 5;
    private static final int EURO_NUMBERS_COUNT = 2;
    private static final int MAIN_NUMBERS_RANGE = 50;
    private static final int EURO_NUMBERS_RANGE = 12;

    public static EurojackpotRowDto generateRandomRow() {
        List<Integer> mainPool = new ArrayList<>();
        List<Integer> euroPool = new ArrayList<>();

        // Fill pools with possible numbers
        for (int i = 1; i <= MAIN_NUMBERS_RANGE; i++) {
            mainPool.add(i);
        }
        for (int i = 1; i <= EURO_NUMBERS_RANGE; i++) {
            euroPool.add(i);
        }

        // Shuffle and select unique numbers
        Collections.shuffle(mainPool);
        Collections.shuffle(euroPool);

        List<Integer> mainNumbers = mainPool.subList(0, MAIN_NUMBERS_COUNT);
        List<Integer> euroNumbers = euroPool.subList(0, EURO_NUMBERS_COUNT);

        return new EurojackpotRowDto(new ArrayList<>(mainNumbers), new ArrayList<>(euroNumbers));
    }

}
