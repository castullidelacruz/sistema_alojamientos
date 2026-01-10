package com.reserva.reserva_server.model.descuento;

import com.reserva.reserva_server.model.enums.Caracteristica;
import com.reserva.reserva_server.model.enums.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@DiscriminatorValue("POR_NOCHE")
public class DescuentoPorNoche extends Descuento {
  @Column
  private Integer cantidadMinima;
  @Column
  private Double porcentaje;

  public DescuentoPorNoche(Integer cantidadMinima, Double porcentaje) {
    this.cantidadMinima = cantidadMinima;
    this.porcentaje = porcentaje;
  }

  @Override
  public Double valorDescontado(Double precioBase, Integer cantidad) {
    Integer repetido = (cantidad / cantidadMinima);
    Double valorDescontado = 0.0;
    if (repetido >= 1) {
      valorDescontado = precioBase * (porcentaje / 100) * repetido;
    }
    return valorDescontado;
  }
}
