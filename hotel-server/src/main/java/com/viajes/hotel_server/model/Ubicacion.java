package com.viajes.hotel_server.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Ubicacion {
  private Double latitud;
  private Double longitud;
}
