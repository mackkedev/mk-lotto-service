package com.mk.demo.response;

import com.mk.demo.dto.EurojackpotRowDto;

import java.util.List;

public record EurojackpotResponse(List<EurojackpotRowDto> rows) {}
