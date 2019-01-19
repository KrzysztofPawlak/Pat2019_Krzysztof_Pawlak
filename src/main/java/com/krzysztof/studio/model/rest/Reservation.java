package com.krzysztof.studio.model.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MAX_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MIN_LENGTH;

@Data
public class Reservation {

    @NotNull
    @Size(min = RESERVATION_MIN_LENGTH, message = "Not enough characters. Minimum is " + RESERVATION_MIN_LENGTH + ".")
    @Size(max = RESERVATION_MAX_LENGTH, message = "Too many characters. Maximum is " + RESERVATION_MAX_LENGTH + ".")
    private String id;
    @NotNull
    private String boardroomName;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;
}
