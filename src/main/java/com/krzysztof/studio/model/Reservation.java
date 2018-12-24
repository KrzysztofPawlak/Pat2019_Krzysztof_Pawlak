package com.krzysztof.studio.model;

import lombok.Data;

import java.time.LocalDateTime;

public @Data class Reservation {

    private String id;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;
}
