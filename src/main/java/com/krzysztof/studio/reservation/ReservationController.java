package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbReservation;
import com.krzysztof.studio.model.rest.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/reservations")
    public ResponseEntity<?> create(@RequestBody @Valid Reservation reservation) {
        return new ResponseEntity<>(reservationService.create(convertToDb(reservation)), HttpStatus.CREATED);
    }

    @GetMapping(value="/reservations")
    public List<Reservation> read() {
        var dbReservations = reservationService.read();
        var reservations = new ArrayList<Reservation>();
        for (DbReservation dbReservation : dbReservations) {
            reservations.add(convertToView(dbReservation));
        }
        return reservations;
    }

    @GetMapping(value="/reservations/{name}")
    public Reservation read(@PathVariable String name) {
        return convertToView(reservationService.read(name));
    }

    @PutMapping(value = "/reservations/{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Reservation reservation) {
        reservationService.update(name, convertToDb(reservation));
    }

    @DeleteMapping(value = "/reservations/{name}")
    public void delete(@PathVariable String name) {
        reservationService.delete(name);
    }

    private DbReservation convertToDb(Reservation reservation) {
        var dbReservation = new DbReservation();
        dbReservation.setId(reservation.getId());
        dbReservation.setBoardroomName(reservation.getBoardroomName());
        dbReservation.setReservationFrom(reservation.getReservationFrom());
        dbReservation.setReservationTo(reservation.getReservationTo());
        return dbReservation;
    }

    private Reservation convertToView(DbReservation dbReservation) {
        var reservation = new Reservation();
        reservation.setId(dbReservation.getId());
        reservation.setBoardroomName(dbReservation.getBoardroomName());
        reservation.setReservationFrom(dbReservation.getReservationFrom());
        reservation.setReservationTo(dbReservation.getReservationTo());
        return reservation;
    }
}
