package com.krzysztof.studio.config.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationConditionException extends RuntimeException {
    private String description;
}
