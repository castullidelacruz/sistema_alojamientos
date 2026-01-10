package com.reserva.reserva_server.service;

import com.reserva.reserva_server.dto.HotelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVER")
public interface HotelClient {

  @GetMapping("/hoteles/{id}/precio")
  HotelDto getPrecioHotel(@PathVariable Long id);
}
