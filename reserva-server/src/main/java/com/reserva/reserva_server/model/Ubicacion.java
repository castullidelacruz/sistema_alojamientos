package com.reserva.reserva_server.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class Ubicacion {
  private Double latitud;
  private Double longitud;
}
