package com.viajes.hotel_server.repository;

import com.viajes.hotel_server.model.Hotel;
import com.viajes.hotel_server.model.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IHotelRespository extends JpaRepository<Hotel, Long> {
  @Query("""
    SELECT h FROM Hotel h
    WHERE (:ciudad IS NULL OR LOWER(h.ciudad) = LOWER(:ciudad))
      AND (:categoria IS NULL OR h.categoria = :categoria)
      AND (:precioMin IS NULL OR h.precioPorNoche >= :precioMin)
      AND (:precioMax IS NULL OR h.precioPorNoche <= :precioMax)
""")
  Page<Hotel> findByFilters(
      @Param("ciudad") String ciudad,
      @Param("categoria") Categoria categoria,
      @Param("precioMin") Double precioMin,
      @Param("precioMax") Double precioMax,
      Pageable pageable
  );

}
