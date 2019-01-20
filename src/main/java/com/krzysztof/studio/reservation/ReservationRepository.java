package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ReservationRepository extends JpaRepository<DbReservation, String> {
    List<DbReservation> findAllByBoardroomName(String boardroomName);
}
