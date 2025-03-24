package com.mk.demo.service;

import com.mk.demo.request.EurojackpotRequest;
import com.mk.demo.dto.EurojackpotRowDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EurojackpotServiceTest {

    private EurojackpotService eurojackpotService;

    @BeforeAll
    void setUp() {
        eurojackpotService = new EurojackpotService();
    }

    @Test
    public void given_generateEurojackpotRows_assert_correct_size() {
        EurojackpotRequest request = new EurojackpotRequest(5);

        List<EurojackpotRowDto> rows = eurojackpotService.generateRows(request);
        assertEquals(5, rows.size());
    }

    @Test
    public void given_generate_eurojackpot_rows_validate_numbers() {
        EurojackpotRequest request = new EurojackpotRequest(1);
        List<EurojackpotRowDto> result = eurojackpotService.generateRows(request);

        assertNotNull(result, "Result should not be null");

        for (EurojackpotRowDto row : result) {
            // Assert main numbers are between 1-50
            assertEquals(5, row.getMainNumbers().size(), "Each row should have 5 main numbers");
            assertTrue(row.getMainNumbers().stream().allMatch(num -> num >= 1 && num <= 50),
                    "All main numbers should be between 1 and 50");

            // Assert euro numbers are between 1-12
            assertEquals(2, row.getEuroNumbers().size(), "Each row should have 2 Euro numbers");
            assertTrue(row.getEuroNumbers().stream().allMatch(num -> num >= 1 && num <= 12),
                    "All euro numbers should be between 1 and 12");
        }
    }

    @Test
    public void given_generateHighChanceRows_validate_numbers(){
        EurojackpotRequest request = new EurojackpotRequest(1);

        List<EurojackpotRowDto> result = eurojackpotService.generateHighChanceRows(request);

        // Count even and odd numbers
        for (EurojackpotRowDto eurojackpotRowDto : result) {
            long evenCount = eurojackpotRowDto.getMainNumbers().stream().filter(num -> num % 2 == 0).count();
            long oddCount = eurojackpotRowDto.getMainNumbers().size() - evenCount; // Since total is always 5

            boolean isValidDistribution = (evenCount == 2 && oddCount == 3) || (evenCount == 3 && oddCount == 2);
            // Assert the even-odd rule
            assertTrue(isValidDistribution, "Main numbers must contain either 2 even + 3 odd or 3 even + 2 odd");
        }
    }
}
