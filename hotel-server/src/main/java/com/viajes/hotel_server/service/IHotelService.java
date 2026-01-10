package com.viajes.hotel_server.service;

import com.viajes.hotel_server.model.Hotel;

public interface IHotelService {
  public Hotel findById(Long id);
}
