package com.example.qtsapp.dto.stats;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record QuoteStatDto(Long id, QuoteAggStatDto aggStat, QuoteUserStat userStat) {

}
