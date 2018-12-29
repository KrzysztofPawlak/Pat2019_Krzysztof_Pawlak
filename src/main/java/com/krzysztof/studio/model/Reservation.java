package com.krzysztof.studio.model;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public @Data class Reservation {

    @Size(min = 2, message = "Too few characters. Minimum is 2.")
    @Size(min = 10, message = "Too many characters. Maximum is 20.")
    private String id;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;
}
