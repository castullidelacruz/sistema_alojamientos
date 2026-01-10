package com.reserva.reserva_server.dto;

import com.reserva.reserva_server.model.descuento.Descuento;
import com.reserva.reserva_server.model.enums.TipoDescuento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoDto {
  private TipoDescuento tipo; // PORCENTAJE, FIJO, etc
  private Double valor;
  private Double porcentaje;
  private Integer cantMinima;
}

