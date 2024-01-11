package org.choongang.admin.reservation.repositories;


import org.choongang.admin.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation> {
}
