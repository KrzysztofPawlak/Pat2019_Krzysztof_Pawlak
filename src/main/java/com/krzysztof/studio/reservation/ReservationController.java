package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.rest.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.RESERVATIONS;

@RestController
@RequestMapping(value = RESERVATIONS)
class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid Reservation reservation) {
        return new ResponseEntity<>(reservationService.create(reservation), HttpStatus.CREATED);
    }

    @GetMapping
    List<Reservation> read() {
        return reservationService.read();
    }

    @GetMapping(value="{name}")
    Reservation read(@PathVariable String name) {
        return reservationService.read(name);
    }

    @PutMapping(value = "{name}")
    void update(@PathVariable String name, @RequestBody @Valid Reservation reservation) {
        reservationService.update(name, reservation);
    }

    @DeleteMapping(value = "{name}")
    void delete(@PathVariable String name) {
        reservationService.delete(name);
    }

}
