package com.krzysztof.studio.reservation;

import com.krzysztof.studio.model.db.DbReservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final EntityManager entityManager;

    ReservationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DbReservation> findOverlapReservations(DbReservation dbReservation) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(DbReservation.class);
        var reservationRoot = criteriaQuery.from(DbReservation.class);
        var nameChild = reservationRoot.join("boardroom").get("name");

        // from: https://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
        // (StartA <= EndB) and (EndA >= StartB)
        var equationLeft = criteriaBuilder.lessThanOrEqualTo(reservationRoot.get("reservationFrom"), dbReservation.getReservationTo());
        var equationRight = criteriaBuilder.greaterThanOrEqualTo(reservationRoot.get("reservationTo"), dbReservation.getReservationFrom());
        var equation = criteriaBuilder.and(equationLeft, equationRight);
        var predicateName = criteriaBuilder.equal(nameChild, dbReservation.getBoardroom().getName());
        var finalCriteria = criteriaBuilder.and(equation, predicateName);

        criteriaQuery.where(finalCriteria);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
