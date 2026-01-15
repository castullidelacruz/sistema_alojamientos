package com.viajes.hotel_server.dto;

import com.viajes.hotel_server.model.Ubicacion;
import com.viajes.hotel_server.model.enums.Caracteristica;
import com.viajes.hotel_server.model.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
  private String nombre;
  private String direccion;
  private String ciudad;
  private Double precioPorNoche;
  private Ubicacion ubicacion;
  private Categoria categoria;
  private List<Caracteristica> caracteristicas;
  private Boolean mediaPension;
}
