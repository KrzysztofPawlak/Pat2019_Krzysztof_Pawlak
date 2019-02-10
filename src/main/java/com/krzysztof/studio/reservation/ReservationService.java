package com.krzysztof.studio.reservation;

import com.krzysztof.studio.boardroom.BoardroomRepository;
import com.krzysztof.studio.config.error.model.AlreadyReservedException;
import com.krzysztof.studio.config.error.model.ReservationConditionException;
import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.model.db.DbReservation;
import com.krzysztof.studio.model.rest.Reservation;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.krzysztof.studio.config.Constants.RESERVATION_MAX_TIME_IN_HOUR;
import static com.krzysztof.studio.config.Constants.RESERVATION_MIN_TIME_IN_MINUTES;
import static java.time.temporal.ChronoUnit.MINUTES;

@Service
class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BoardroomRepository boardroomRepository;

    ReservationService(ReservationRepository reservationRepository, BoardroomRepository boardroomRepository) {
        this.reservationRepository = reservationRepository;
        this.boardroomRepository = boardroomRepository;
    }

    Reservation create(Reservation reservation) {
        var dbReservation = convertToDb(reservation);
        if (exists(dbReservation)) {
            throw new ResourceAlreadyExistsException("Reservation id already exists!");
        }
        checkBoardroomExists(dbReservation);
        checkReservationPreConditions(dbReservation);
        checkReservationIsAvailable(dbReservation);
        return convertToView(reservationRepository.save(dbReservation));
    }

    List<Reservation> read() {
        return reservationRepository.findAll().stream().map(dbReservation -> convertToView(dbReservation)).collect(Collectors.toList());
    }

    Reservation read(String id) {
        return convertToView(reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Reservations found!")));
    }

    void delete(String id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        }
    }

    void update(String id, Reservation reservationUpdated) {
        var dbReservationUpdated = convertToDb(reservationUpdated);
        if (!reservationRepository.existsById(id)) {
            return;
        }
        checkBoardroomExists(dbReservationUpdated);
        checkReservationPreConditions(dbReservationUpdated);
        checkReservationIsAvailable(dbReservationUpdated);
        reservationRepository.save(dbReservationUpdated);
    }

    private void checkBoardroomExists(DbReservation dbReservation) {
        if (!boardroomRepository.existsById(dbReservation.getBoardroom().getName())) {
            throw new ResourceNotFoundException("Specified boardroom is not exists!");
        }
    }

    private boolean exists(DbReservation dbReservation) {
        return reservationRepository.existsById(dbReservation.getId());
    }

    private void checkReservationPreConditions(DbReservation dbReservation) {
        var begin = dbReservation.getReservationFrom();
        var end = dbReservation.getReservationTo();
        var reservationTime = Duration.ofMinutes(MINUTES.between(begin, end));
        var maxReservationTime = Duration.ofMinutes(Duration.ofHours(RESERVATION_MAX_TIME_IN_HOUR).toMinutes());
        var minReservationTime = Duration.ofMinutes(RESERVATION_MIN_TIME_IN_MINUTES);

        if (begin.isAfter(end) && end.isBefore(begin)) {
            throw new ReservationConditionException("Reservation end time cannot be before start time.");
        }
        if (reservationTime.compareTo(maxReservationTime) >= 0) {
            throw new ReservationConditionException("Exceeded reservation max time: " + RESERVATION_MAX_TIME_IN_HOUR + " hours.");
        }
        if (reservationTime.compareTo(minReservationTime) < 0) {
            throw new ReservationConditionException("Reservation min time cannot be less than: " + RESERVATION_MIN_TIME_IN_MINUTES + " minutes.");
        }
    }

    private void checkReservationIsAvailable(DbReservation dbReservation) {
        reservationRepository.findOverlapReservations(dbReservation).stream().anyMatch(reservationEntry -> {
            throw new AlreadyReservedException("Boardroom is already reserved!");
        });
    }

    private DbReservation convertToDb(Reservation reservation) {
        var dbReservation = new DbReservation();
        dbReservation.setId(reservation.getId());
        dbReservation.setBoardroom(new DbBoardroom(reservation.getBoardroomName()));
        dbReservation.setReservationFrom(reservation.getReservationFrom().truncatedTo(MINUTES));
        dbReservation.setReservationTo(reservation.getReservationTo().truncatedTo(MINUTES));
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
