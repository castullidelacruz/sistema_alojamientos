package com.viajes.hotel_server.service;

import com.viajes.hotel_server.model.Hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHotelService {
  public Hotel findById(Long id);

  public Page<Hotel> findAll(
      String ciudad, String categoria,
      Double precioMin, Double precioMax,
      Pageable pageable);
}
