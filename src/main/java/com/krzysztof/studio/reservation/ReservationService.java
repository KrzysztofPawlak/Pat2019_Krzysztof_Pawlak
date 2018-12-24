package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<Reservation> reservations = new ArrayList<>();

    public void create(Reservation reservation) {
        reservations.add(reservation);
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
}
