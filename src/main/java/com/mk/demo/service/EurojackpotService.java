package com.mk.demo.service;

import com.mk.demo.request.EurojackpotRequest;
import com.mk.demo.dto.EurojackpotRowDto;
import com.mk.demo.util.EurojackpotGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<EurojackpotRowDto> generateHighChanceRows(EurojackpotRequest eurojackpotRequest) {
        List<EurojackpotRowDto> rows = new ArrayList<>();

        List<Integer> mainNumbers = generateMainNumbers();
        List<Integer> euroNumbers = generateEuroNumbers();

        for (int i = 0; i < eurojackpotRequest.rows() ; i++) {
            rows.add(new EurojackpotRowDto(mainNumbers, euroNumbers));
        }

        return rows;
    }

    private static List<Integer> generateMainNumbers() {
        List<Integer> row = new ArrayList<>();

        // Decide even-odd distribution randomly
        boolean isTwoEven = random.nextBoolean();
        int evenCount = isTwoEven ? 2 : 3;
        int oddCount = 5 - evenCount;

        List<Integer> evenNumbersUnder25 = getNumbers(2, 24, true);
        List<Integer> evenNumbersOver25 = getNumbers(26, 50, true);
        List<Integer> oddNumbersUnder25 = getNumbers(1, 23, false);
        List<Integer> oddNumbersOver25 = getNumbers(27, 49, false);

        // Pick required even numbers
        row.addAll(pickNumbers(evenNumbersUnder25, evenNumbersOver25, evenCount));
        // Pick required odd numbers
        row.addAll(pickNumbers(oddNumbersUnder25, oddNumbersOver25, oddCount));

        // Sort for readability
        Collections.sort(row);
        return row;
    }

    private static List<Integer> generateEuroNumbers() {
        List<Integer> euroNumbers = new ArrayList<>();
        Set<Integer> usedNumbers = new HashSet<>();

        while (euroNumbers.size() < 2) {
            int num = random.nextInt(12) + 1;  // Generates numbers between 1-12
            if (usedNumbers.add(num)) {
                euroNumbers.add(num);
            }
        }
        return euroNumbers;
    }

    private static List<Integer> getNumbers(int start, int end, boolean even) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if ((i % 2 == 0) == even) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private static List<Integer> pickNumbers(List<Integer> under25, List<Integer> over25, int count) {
        List<Integer> pickedNumbers = new ArrayList<>();

        if (count == 2) { // Pick 1 from under 25, 1 from over 25
            pickedNumbers.add(removeRandom(under25));
            pickedNumbers.add(removeRandom(over25));
        } else { // Pick 2 from one, 1 from the other
            if (random.nextBoolean()) {
                pickedNumbers.add(removeRandom(under25));
                pickedNumbers.add(removeRandom(under25));
                pickedNumbers.add(removeRandom(over25));
            } else {
                pickedNumbers.add(removeRandom(over25));
                pickedNumbers.add(removeRandom(over25));
                pickedNumbers.add(removeRandom(under25));
            }
        }
        return pickedNumbers;
    }

    private static Integer removeRandom(List<Integer> list) {
        return list.remove(random.nextInt(list.size()));
    }

}
