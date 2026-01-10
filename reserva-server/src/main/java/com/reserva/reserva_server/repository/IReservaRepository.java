package com.reserva.reserva_server.repository;

import com.reserva.reserva_server.model.Hotel;
import com.reserva.reserva_server.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
  @Query("""
    SELECT count (r) > 0 FROM Reserva r
    WHERE r.hotel = :hotel
      AND (:idReserva IS NULL OR r.id <> :idReserva)
      AND r.fechaInicio < :fechaFin
      AND r.fechaFin > :fechaInicio
  """)
  Boolean existsReservaSolapada(
      @Param("hotel") Long hotel,
      @Param("fechaInicio") LocalDate fechaInicio,
      @Param("fechaFin") LocalDate fechaFin,
      @Param("idReserva") Long idReserva
  );

  @Query("""
    SELECT r FROM Reserva r
      WHERE r.fechaInicio >= :inicio
        AND r.fechaFin <= :fin
  """)
  List<Reserva> findAllByDate(
      @Param("inicio") LocalDate inicio,
      @Param("fin") LocalDate fin
  );
}
