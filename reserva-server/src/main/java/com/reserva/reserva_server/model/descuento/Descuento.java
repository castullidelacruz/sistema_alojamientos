package com.reserva.reserva_server.model.descuento;

import com.reserva.reserva_server.model.Reserva;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Descuento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "reserva_id")
  private Reserva reserva;

  public abstract Double valorDescontado(Double precioBase, Integer cantidad);
}
