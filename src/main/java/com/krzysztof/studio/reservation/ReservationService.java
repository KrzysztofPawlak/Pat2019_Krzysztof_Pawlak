package com.krzysztof.studio.reservation;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public DbReservation create(DbReservation dbReservation) {
        if (exists(dbReservation)) throw new ResourceAlreadyExistsException("Reservation already exists!");
        return reservationRepository.save(dbReservation);
    }

    public List<DbReservation> read() {
        var reservations = new ArrayList<DbReservation>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    public DbReservation read(String id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Organization found!"));
    }

    public void delete(String id) {
        if (reservationRepository.existsById(id)) reservationRepository.deleteById(id);
    }

    public void update(String id, DbReservation dbReservationUpdated) {
        if (reservationRepository.existsById(id)) reservationRepository.save(dbReservationUpdated);
    }

    public boolean exists(DbReservation dbReservation) {
        return reservationRepository.existsById(dbReservation.getId());
    }
}
