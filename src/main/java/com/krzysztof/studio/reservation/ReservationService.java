package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<Reservation> reservations = new ArrayList<>();

    public ResponseEntity<?> create(Reservation reservation) {

        if (!exists(reservation)) {
            reservations.add(reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("reservation already exists", HttpStatus.BAD_REQUEST);
    }

    public List<Reservation> read() {
        return reservations;
    }

    public Reservation read(String id) {
        return reservations.stream()
                .filter(reservation -> id.equals(reservation.getId()))
                .findFirst().orElse(null);
    }

    public void delete(String id) {
        reservations.removeIf(t -> t.getId().equals(id));
    }

    public void update(String id, Reservation reservationUpdated) {
        reservations.stream()
                .filter(reservation -> id.equals(reservation.getId()))
                .forEach(reservation -> reservations.set(reservations.indexOf(reservation), reservationUpdated));
    }

    public boolean exists(Reservation reservation) {
        return read(reservation.getId()) != null;
    }
}
