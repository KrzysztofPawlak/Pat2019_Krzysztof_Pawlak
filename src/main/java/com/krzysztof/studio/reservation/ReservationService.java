package com.krzysztof.studio.reservation;

import com.krzysztof.studio.boardroom.BoardroomRepository;
import com.krzysztof.studio.config.error.model.AlreadyReservedException;
import com.krzysztof.studio.config.error.model.ReservationConditionException;
import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MAX_TIME_IN_HOUR;
import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MIN_TIME_IN_MINUTES;
import static java.time.temporal.ChronoUnit.MINUTES;

@Service
class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BoardroomRepository boardroomRepository;

    ReservationService(ReservationRepository reservationRepository, BoardroomRepository boardroomRepository) {
        this.reservationRepository = reservationRepository;
        this.boardroomRepository = boardroomRepository;
    }

    DbReservation create(DbReservation dbReservation) {
        if (exists(dbReservation)) throw new ResourceAlreadyExistsException("Reservation id already exists!");
        checkBoardroomExists(dbReservation);
        checkReservationPreConditions(dbReservation);
        checkReservationIsAvailable(dbReservation);
        return reservationRepository.save(dbReservation);
    }

    List<DbReservation> read() {
        return reservationRepository.findAll();
    }

    DbReservation read(String id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Reservations found!"));
    }

    void delete(String id) {
        if (reservationRepository.existsById(id)) reservationRepository.deleteById(id);
    }

    void update(String id, DbReservation dbReservationUpdated) {
        if (!reservationRepository.existsById(id)) return;
        checkBoardroomExists(dbReservationUpdated);
        checkReservationPreConditions(dbReservationUpdated);
        checkReservationIsAvailable(dbReservationUpdated);
        reservationRepository.save(dbReservationUpdated);
    }

    private void checkBoardroomExists(DbReservation dbReservation) {
        if (!boardroomRepository.existsById(dbReservation.getBoardroom().getName()))
            throw new ResourceNotFoundException("Specified boardroom is not exists!");
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

        if (begin.isAfter(end) && end.isBefore(begin))
            throw new ReservationConditionException("Reservation end time cannot be before start time.");
        if (reservationTime.compareTo(maxReservationTime) >= 0)
            throw new ReservationConditionException("Exceeded reservation max time: " + RESERVATION_MAX_TIME_IN_HOUR + " hours.");
        if (reservationTime.compareTo(minReservationTime) < 0)
            throw new ReservationConditionException("Reservation min time cannot be less than: " + RESERVATION_MIN_TIME_IN_MINUTES + " minutes.");
    }

    private void checkReservationIsAvailable(DbReservation dbReservation) {
        reservationRepository.findOverlapReservations(dbReservation).stream().anyMatch(reservationEntry -> {
            throw new AlreadyReservedException("Boardroom is already reserved!");
        });
    }
}
