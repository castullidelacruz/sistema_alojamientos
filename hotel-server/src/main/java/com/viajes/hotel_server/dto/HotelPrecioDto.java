package com.viajes.hotel_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class HotelPrecioDto {
  private Long hotelId;
  private Double precioPorNoche;
}
