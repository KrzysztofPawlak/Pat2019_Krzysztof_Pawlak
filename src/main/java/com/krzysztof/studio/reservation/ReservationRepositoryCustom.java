package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbReservation;

import java.util.List;

interface ReservationRepositoryCustom {
    List<DbReservation> findOverlapReservations(DbReservation dbReservation);
}
