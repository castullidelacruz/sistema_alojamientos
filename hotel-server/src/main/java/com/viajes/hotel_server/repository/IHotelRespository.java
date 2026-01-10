package com.viajes.hotel_server.repository;

import com.viajes.hotel_server.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelRespository extends JpaRepository<Hotel, Long> {
}
