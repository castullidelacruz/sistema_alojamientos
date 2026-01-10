package com.reserva.reserva_server.model.descuento;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@DiscriminatorValue("PORCENTAJE")
public class DescuentoPorcentaje extends Descuento {
  @Column
  private Double porcentaje;

  @Override
  public Double valorDescontado(Double precioBase, Integer cantidad) {
    return precioBase * (porcentaje / 100.0);
  }
}
