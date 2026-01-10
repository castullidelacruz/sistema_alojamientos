package com.viajes.hotel_server.model;

import com.viajes.hotel_server.model.enums.Caracteristica;
import com.viajes.hotel_server.model.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String direccion;
  @Column(nullable = false)
  private String ciudad;
  @Column(nullable = false)
  private Double precioPorNoche;
  @Embedded
  private Ubicacion ubicacion;
  @Enumerated
  private Categoria categoria;
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<Caracteristica> caracteristicas;
  @Column
  private Boolean mediaPension;
}

