package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<DbReservation, String> { }
