package com.viajes.hotel_server.controller;

import com.viajes.hotel_server.dto.HotelDto;
import com.viajes.hotel_server.dto.HotelPrecioDto;
import com.viajes.hotel_server.dto.PageResponse;
import com.viajes.hotel_server.model.Hotel;
import com.viajes.hotel_server.service.IHotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

  @GetMapping("/{id}")
  public ResponseEntity<HotelDto> findById(@PathVariable Long id) {
    return ResponseEntity.ok(this.toDtoHotel(service.findById(id)));
  }

  @GetMapping
  public ResponseEntity<PageResponse<HotelDto>> findAll(
      @RequestParam(required = false) String ciudad,
      @RequestParam(required = false) String categoria,
      @RequestParam(required = false) Double precioMin,
      @RequestParam(required = false) Double precioMax,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String sort
  ) {
    Sort sortOrder = Sort.by("id"); // 🔹 sort por defecto

    if ("precio_asc".equalsIgnoreCase(sort)) {
      sortOrder = Sort.by("precioPorNoche").ascending();
    } else if ("precio_desc".equalsIgnoreCase(sort)) {
      sortOrder = Sort.by("precioPorNoche").descending();
    }

    Pageable pageable = PageRequest.of(page, size, sortOrder);

    Page<HotelDto> pageResult = service
        .findAll(ciudad, categoria, precioMin, precioMax, pageable)
        .map(this::toDtoHotel);

    PageResponse<HotelDto> response = new PageResponse<>(
        pageResult.getContent(),
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        pageResult.getTotalPages(),
        pageResult.isLast()
    );

    return ResponseEntity.ok(response);

  }


  //FUNCIONES AUXILIARES

  private HotelPrecioDto toDtoPrecio(Hotel hotel) {
    return new HotelPrecioDto(hotel.getId(), hotel.getPrecioPorNoche());
  }

  private HotelDto toDtoHotel(Hotel hotel) {
    return new HotelDto(hotel.getNombre(), hotel.getDireccion(),
        hotel.getCiudad(), hotel.getPrecioPorNoche(), hotel.getUbicacion(),
        hotel.getCategoria(), hotel.getCaracteristicas(), hotel.getMediaPension());
  }
}

