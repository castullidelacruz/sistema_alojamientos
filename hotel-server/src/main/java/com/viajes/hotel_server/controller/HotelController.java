package com.viajes.hotel_server.controller;

import com.viajes.hotel_server.dto.HotelPrecioDto;
import com.viajes.hotel_server.model.Hotel;
import com.viajes.hotel_server.service.IHotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

  private final IHotelService service;

  public HotelController(IHotelService service) {
    this.service = service;
  }

  @GetMapping("/{id}/precio")
  public ResponseEntity<HotelPrecioDto> findPrecioById(@PathVariable Long id) {
    return ResponseEntity.ok(this.toDtoPrecio(service.findById(id)));
  }

  private HotelPrecioDto toDtoPrecio(Hotel hotel) {
    return new HotelPrecioDto(hotel.getId(), hotel.getPrecioPorNoche());
  }
}

