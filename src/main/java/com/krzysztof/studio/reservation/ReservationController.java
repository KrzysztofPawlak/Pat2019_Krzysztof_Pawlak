package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST, value = "/reservations")
    public ResponseEntity<?> create(Reservation reservation) {
        return reservationService.create(reservation);
    }

    @RequestMapping(method = RequestMethod.GET, value="/reservations")
    public List<Reservation> read() {
        return reservationService.read();
    }

    @RequestMapping(method = RequestMethod.GET, value="/reservations/{name}")
    public Reservation read(String name) {
        return reservationService.read(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reservations/{name}")
    public void update(String name, Reservation reservation) {
        reservationService.update(name, reservation);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{name}")
    public void delete(String name) {
        reservationService.delete(name);
    }
}
