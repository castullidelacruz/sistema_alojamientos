package com.viajes.hotel_server.service;

import com.viajes.hotel_server.model.Hotel;
import com.viajes.hotel_server.repository.IHotelRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HotelService implements IHotelService {

  private final IHotelRespository repository;

  public HotelService(IHotelRespository repository) {
    this.repository = repository;
  }

  public Hotel findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Hotel no encontrado"));
  }
}

