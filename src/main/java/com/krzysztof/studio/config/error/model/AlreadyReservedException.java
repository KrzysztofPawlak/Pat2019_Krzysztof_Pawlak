package com.krzysztof.studio.config.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlreadyReservedException extends RuntimeException {
    private String description;
}
