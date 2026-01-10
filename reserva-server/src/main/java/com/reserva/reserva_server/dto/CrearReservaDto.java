package com.reserva.reserva_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class CrearReservaDto {
  private Long usuarioId;
  private Long hotelId;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private Double precioPorNoche;
}
