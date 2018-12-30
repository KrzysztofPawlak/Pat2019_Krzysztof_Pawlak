package com.krzysztof.studio.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MAX_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MIN_LENGTH;

public @Data class Reservation {

    @NotNull
    @Size(min = RESERVATION_MIN_LENGTH, message = "Too few characters. Minimum is " + RESERVATION_MIN_LENGTH + ".")
    @Size(min = RESERVATION_MAX_LENGTH, message = "Not enough characters. Maximum is " + RESERVATION_MAX_LENGTH + ".")
    private String id;
    private String boardroomName;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;
}
