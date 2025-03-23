package com.mk.demo.service;

import com.mk.demo.request.EurojackpotRequest;
import com.mk.demo.dto.EurojackpotRowDto;
import com.mk.demo.util.EurojackpotGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EurojackpotService {

    private static final Random random = new Random();

    public List<EurojackpotRowDto> generateRows(EurojackpotRequest request) {
        List<EurojackpotRowDto> jackpotRows = new ArrayList<>();

        for (int i = 0; i<request.rows(); i++) {
            jackpotRows.add(EurojackpotGenerator.generateRandomRow());
        }
        return jackpotRows;
    }

    public List<Integer> generateHighestChangeRows() {
        List<Integer> row = new ArrayList<>();

        boolean isTwoEven = random.nextBoolean(); // Randomly decide 2 even + 3 odd OR 3 even + 2 odd

        int evenCount = isTwoEven ? 2 : 3;
        int oddCount = 5 - evenCount;

        List<Integer> evenNumbersUnder25 = getNumbers(2, 24, true);
        List<Integer> evenNumbersOver25 = getNumbers(26, 50, true);
        List<Integer> oddNumbersUnder25 = getNumbers(1, 23, false);
        List<Integer> oddNumbersOver25 = getNumbers(27, 49, false);

        // Pick required numbers
        row.addAll(pickNumbers(evenNumbersUnder25, evenNumbersOver25, evenCount));
        row.addAll(pickNumbers(oddNumbersUnder25, oddNumbersOver25, oddCount));

        // Sort the row for readability (optional)
        Collections.sort(row);
        return row;
    }

    private List<Integer> getNumbers(int start, int end, boolean even) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if ((i % 2 == 0) == even) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private List<Integer> pickNumbers(List<Integer> under25, List<Integer> over25, int count) {
        List<Integer> pickedNumbers = new ArrayList<>();

        if (count == 2) { // Pick 1 from under 25, 1 from over 25
            pickedNumbers.add(under25.remove(random.nextInt(under25.size())));
            pickedNumbers.add(over25.remove(random.nextInt(over25.size())));
        } else { // Pick 2 from one, 1 from the other
            if (random.nextBoolean()) {
                pickedNumbers.add(under25.remove(random.nextInt(under25.size())));
                pickedNumbers.add(under25.remove(random.nextInt(under25.size())));
                pickedNumbers.add(over25.remove(random.nextInt(over25.size())));
            } else {
                pickedNumbers.add(over25.remove(random.nextInt(over25.size())));
                pickedNumbers.add(over25.remove(random.nextInt(over25.size())));
                pickedNumbers.add(under25.remove(random.nextInt(under25.size())));
            }
        }

        return pickedNumbers;
    }

}
