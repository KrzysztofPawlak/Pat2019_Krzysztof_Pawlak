package com.krzysztof.studio.model.db;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class DbReservation {

    @Id
    private String id;
    @ManyToOne
    private DbBoardroom boardroom;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;

    public boolean isAvailable(LocalDateTime reservationFrom, LocalDateTime reservationTo) {
        return (reservationFrom.isAfter(this.getReservationFrom()) && reservationFrom.isAfter(this.getReservationTo()) &&
                        reservationTo.isAfter(this.getReservationFrom()) && reservationTo.isAfter(this.getReservationTo())) ||
                        (reservationFrom.isBefore(this.getReservationFrom()) && reservationFrom.isBefore(this.getReservationTo()) &&
                                reservationTo.isBefore(this.getReservationFrom()) && reservationTo.isBefore(this.getReservationTo()));
    }
}
