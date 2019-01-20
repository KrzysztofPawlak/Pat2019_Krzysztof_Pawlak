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

}
