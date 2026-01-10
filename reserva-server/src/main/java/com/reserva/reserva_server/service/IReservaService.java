package com.reserva.reserva_server.service;

import com.reserva.reserva_server.dto.CrearReservaDto;
import com.reserva.reserva_server.dto.DescuentoDto;
import com.reserva.reserva_server.model.Reserva;
import com.reserva.reserva_server.model.enums.EstadoReserva;

import java.time.LocalDate;
import java.util.List;

public interface IReservaService {
  public Reserva findById(Long id);

  public Reserva create(CrearReservaDto reserva);

  public void delete(Long id);

  public Reserva updateEstadoReserva(Long id, EstadoReserva estadoReserva);

  public Reserva updateFechas(Long id, LocalDate inicio, LocalDate fin);

  public Reserva agregarDescuentos(Long id, List<DescuentoDto> descuentosDto);

  public List<Reserva> findAll(LocalDate inicio,  LocalDate fin);

  //public List<Reserva> findAllByDate(LocalDate inicio,  LocalDate fin);
}
