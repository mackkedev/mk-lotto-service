package com.mk.demo.controller;

import com.mk.demo.request.EurojackpotRequest;
import com.mk.demo.response.EurojackpotResponse;
import com.mk.demo.service.EurojackpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/eurojackpot")
public class EurojackpotController {

    private final EurojackpotService eurojackpotService;

    public EurojackpotController(EurojackpotService eurojackpotService) {
        this.eurojackpotService = eurojackpotService;
    }

    @PostMapping("/generate")
    public ResponseEntity<EurojackpotResponse> getEurojackpotRows(@RequestBody EurojackpotRequest request) {
        return new ResponseEntity<>(new EurojackpotResponse(eurojackpotService.generateRows(request)), HttpStatus.OK);
    }

    @PostMapping("/generate/highest/chance")
    public ResponseEntity<EurojackpotResponse> getEurojackpotHighChanceRows(@RequestBody EurojackpotRequest request) {
        System.out.println("in development");
        return new ResponseEntity<>(new EurojackpotResponse(eurojackpotService.generateHighChanceRows(request)),HttpStatus.OK);
    }
}
