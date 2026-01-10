package com.reserva.reserva_server.model;

import com.reserva.reserva_server.model.descuento.Descuento;
import com.reserva.reserva_server.model.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)
  private Long usuario;
  @Column(nullable = false)
  private LocalDate fechaInicio;
  @Column(nullable = false)
  private LocalDate fechaFin;
  @Column(nullable = false)
  private Long hotel;
  @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
  private List<Descuento> descuentos;
  @Enumerated(EnumType.STRING)
  private EstadoReserva estadoReserva;
  @Column(nullable = false)
  private Double precioPorNoche;

  public Reserva(Long usuario, LocalDate fechaInicio,
                 LocalDate fechaFin, Long
                     hotel, List<Descuento> descuentos) {
    if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)) {
      throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
    }
    this.usuario = usuario;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.hotel = hotel;
    this.descuentos = descuentos;
    this.estadoReserva = EstadoReserva.CREADA;
  }

  public Double precioBase() {
    return this.cantidadDeNoches() * precioPorNoche;
  }

  public Integer cantidadDeNoches() {
    return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
  }

  public Double precioFinal() {
    double base = this.precioBase();
    double totalDescuento = descuentos == null ? 0.0 :
        descuentos.stream()
            .mapToDouble(d -> d.valorDescontado(base, cantidadDeNoches()))
            .sum();

    return Math.max(base - totalDescuento, 0.0);
  }

  public void cambiarEstado(EstadoReserva nuevo) {
    validarTransicion(this.estadoReserva, nuevo);
    this.estadoReserva = nuevo;
  }

  private void validarTransicion(EstadoReserva actual, EstadoReserva nuevo) {
    if (actual == EstadoReserva.CANCELADA || actual == EstadoReserva.FINALIZADA) {
      throw new IllegalStateException(
          "No se puede cambiar el estado de una reserva " + actual
      );
    }

    if (actual == EstadoReserva.CREADA && nuevo == EstadoReserva.FINALIZADA) {
      throw new IllegalStateException(
          "Una reserva no puede finalizarse sin estar confirmada"
      );
    }
  }


}
