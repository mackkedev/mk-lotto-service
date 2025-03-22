package com.mk.demo.service;

import com.mk.demo.controller.EurojackpotRequest;
import com.mk.demo.dto.EurojackpotRowDto;
import com.mk.demo.response.EurojackpotResponse;
import com.mk.demo.util.EurojackpotGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EurojackpotService {

    public List<EurojackpotRowDto> generateRows(EurojackpotRequest request) {
        List<EurojackpotRowDto> jackpotRows = new ArrayList<>();

        for (int i = 0; i<request.rows(); i++) {
            jackpotRows.add(EurojackpotGenerator.generateRandomRow());
        }
        return jackpotRows;
    }

}
