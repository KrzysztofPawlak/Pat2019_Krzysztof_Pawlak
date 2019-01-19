package com.krzysztof.studio.reservation;

import com.krzysztof.studio.boardroom.BoardroomRepository;
import com.krzysztof.studio.config.error.model.AlreadyReservedException;
import com.krzysztof.studio.config.error.model.ReservationConditionException;
import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MAX_TIME_IN_HOUR;
import static com.krzysztof.studio.config.ApiConfig.RESERVATION_MIN_TIME_IN_MINUTES;
import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BoardroomRepository boardroomRepository;

    public DbReservation create(DbReservation dbReservation) {
        if (exists(dbReservation)) throw new ResourceAlreadyExistsException("Reservation id already exists!");
        if (!boardroomRepository.existsById(dbReservation.getBoardroom().getName())) throw new ResourceNotFoundException("Specified boardroom is not exists!");
        checkReservationPreConditions(dbReservation);
        checkReservationIsAvailable(dbReservation);
        return reservationRepository.save(dbReservation);
    }

    public List<DbReservation> read() {
        var reservations = new ArrayList<DbReservation>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    public DbReservation read(String id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Reservations found!"));
    }

    public void delete(String id) {
        if (reservationRepository.existsById(id)) reservationRepository.deleteById(id);
    }

    public void update(String id, DbReservation dbReservationUpdated) {
        if (!reservationRepository.existsById(id)) return;
        if (!boardroomRepository.existsById(dbReservationUpdated.getBoardroom().getName())) throw new ResourceNotFoundException("Specified boardroom is not exists!");
        checkReservationPreConditions(dbReservationUpdated);
        checkReservationIsAvailable(dbReservationUpdated);
        reservationRepository.save(dbReservationUpdated);
    }

    public boolean exists(DbReservation dbReservation) {
        return reservationRepository.existsById(dbReservation.getId());
    }

    private List<DbReservation> getReservations(String boardroomName) {
        return reservationRepository.findAllByBoardroomName(boardroomName);
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
        var reservationList = getReservations(dbReservation.getBoardroom().getName());
        reservationList.stream().filter(reservationEntry ->
                reservationEntry.isAvailable(dbReservation.getReservationFrom(), dbReservation.getReservationTo()) == false)
                .findAny().ifPresent(reservationEntry -> {
            throw new AlreadyReservedException("Boardroom is already reserved!");
        });
    }
}
