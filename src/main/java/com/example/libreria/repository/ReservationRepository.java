package com.example.libreria.repository;

import com.example.libreria.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    // TODO: DONE  Implementar los métodos de la reserva
    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByStatus(Reservation.ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.status = 'ACTIVE' AND r.expectedReturnDate < :currentDate")
    List<Reservation> findOverdueReservations(LocalDate currentDate);

    default List<Reservation> findOverdueReservations() {
        return findOverdueReservations(LocalDate.now());
    }
}

