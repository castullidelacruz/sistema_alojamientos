package com.reserva.reserva_server.dto;

import com.reserva.reserva_server.model.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class ReservaDto {
  private Long usuario;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private Long hotel;
  private EstadoReserva estadoReserva;

}
