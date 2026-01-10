package com.reserva.reserva_server.model.descuento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("FIJO")
public class DescuentoFijo extends Descuento {
  @Column
  private Double valorFijo;

  public DescuentoFijo(Double valorFijo) {
    this.valorFijo = valorFijo;
  }

  public DescuentoFijo() {
  }

  @Override
  public Double valorDescontado(Double precioBase, Integer cantidad) {
    return valorFijo;
  }
}
