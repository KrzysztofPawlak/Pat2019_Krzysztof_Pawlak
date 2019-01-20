package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.model.db.DbReservation;
import com.krzysztof.studio.model.rest.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.RESERVATIONS;

@RestController
@RequestMapping(value = RESERVATIONS)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Reservation reservation) {
        return new ResponseEntity<>(convertToView(reservationService.create(convertToDb(reservation))), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Reservation> read() {
        var reservations = new ArrayList<Reservation>();
        reservationService.read().stream().forEach(dbReservation -> reservations.add(convertToView(dbReservation)));
        return reservations;
    }

    @GetMapping(value="{name}")
    public Reservation read(@PathVariable String name) {
        return convertToView(reservationService.read(name));
    }

    @PutMapping(value = "{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Reservation reservation) {
        reservationService.update(name, convertToDb(reservation));
    }

    @DeleteMapping(value = "{name}")
    public void delete(@PathVariable String name) {
        reservationService.delete(name);
    }

    private DbReservation convertToDb(Reservation reservation) {
        var dbReservation = new DbReservation();
        dbReservation.setId(reservation.getId());
        dbReservation.setBoardroom(new DbBoardroom(reservation.getBoardroomName()));
        dbReservation.setReservationFrom(reservation.getReservationFrom().truncatedTo(ChronoUnit.SECONDS));
        dbReservation.setReservationTo(reservation.getReservationTo().truncatedTo(ChronoUnit.SECONDS));
        return dbReservation;
    }

    private Reservation convertToView(DbReservation dbReservation) {
        var reservation = new Reservation();
        reservation.setId(dbReservation.getId());
        reservation.setBoardroomName(dbReservation.getBoardroom().getName());
        reservation.setReservationFrom(dbReservation.getReservationFrom());
        reservation.setReservationTo(dbReservation.getReservationTo());
        return reservation;
    }
}
