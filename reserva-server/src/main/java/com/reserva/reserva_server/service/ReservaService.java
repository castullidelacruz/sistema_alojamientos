package com.reserva.reserva_server.service;

import com.reserva.reserva_server.dto.CrearReservaDto;
import com.reserva.reserva_server.dto.DescuentoDto;
import com.reserva.reserva_server.dto.HotelDto;
import com.reserva.reserva_server.dto.ReservaDto;
import com.reserva.reserva_server.model.Reserva;
import com.reserva.reserva_server.model.descuento.Descuento;
import com.reserva.reserva_server.model.descuento.DescuentoFijo;
import com.reserva.reserva_server.model.descuento.DescuentoPorNoche;
import com.reserva.reserva_server.model.descuento.DescuentoPorcentaje;
import com.reserva.reserva_server.model.enums.EstadoReserva;
import com.reserva.reserva_server.repository.IReservaRepository;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservaService implements IReservaService {

  private final IReservaRepository reservaRepository;
  private final HotelClient hotelClient;

  public ReservaService(IReservaRepository reservaRepository,
                        HotelClient hotelClient) {
    this.reservaRepository = reservaRepository;
    this.hotelClient = hotelClient;
  }

  @Override
  public Reserva findById(Long id) {
    return reservaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
  }

  @Override
  public Reserva create(CrearReservaDto dto) {
    HotelDto hotelDto;
    try {
      hotelDto = hotelClient.getPrecioHotel(dto.getHotelId());
    } catch (FeignException.NotFound e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Hotel inexistente");
    } catch (FeignException e) {
      e.printStackTrace();
      throw new IllegalStateException("Servicio de hoteles no disponible");
    }

    Reserva reserva = new Reserva(
        dto.getUsuarioId(),
        dto.getFechaInicio(),
        dto.getFechaFin(),
        dto.getHotelId(),
        List.of()
    );
    reserva.setPrecioPorNoche(hotelDto.getPrecioPorNoche());

    if (haySolapamiento(reserva)) {
      throw new IllegalArgumentException("No hay reservas disponibles");
    }

    return reservaRepository.save(reserva);
  }



  @Override
  public void delete(Long id) {
    Reserva reserva = findById(id);

    if (!reserva.getFechaInicio().isAfter(LocalDate.now())) {
      throw new IllegalStateException(
          "No se puede eliminar una reserva en curso o pasada"
      );
    }

    reservaRepository.delete(reserva);
  }

  @Override
  public Reserva updateEstadoReserva(Long id, EstadoReserva estadoReserva) {
    Reserva original = this.findById(id);
    original.cambiarEstado(estadoReserva);
    return reservaRepository.save(original);
  }

  @Override
  public Reserva updateFechas(Long id, LocalDate inicio, LocalDate fin) {
    Reserva reserva = findById(id);

    validarFechas(inicio, fin);
    reserva.setFechaInicio(inicio);
    reserva.setFechaFin(fin);

    if (haySolapamiento(reserva)) {
      throw new IllegalArgumentException("No hay disponibilidad en esas fechas");
    }

    return reservaRepository.save(reserva);
  }

  @Override
  public Reserva agregarDescuentos(Long id, List<DescuentoDto> descuentosDto) {
    Reserva reserva = findById(id);
    List<Descuento> descuentos = descuentosDto.stream()
        .map(this::crearDescuento)
        .toList();

    reserva.setDescuentos(descuentos);
    return reservaRepository.save(reserva);
  }

  @Override
  public List<Reserva> findAll(LocalDate inicio,  LocalDate fin) {
    if (inicio == null || fin == null) {
      return reservaRepository.findAll();
    }
    return reservaRepository.findAllByDate(inicio, fin);
  }


  //------------------------------VALIDACIONES-------------------------------
  //-------------------------------------------------------------------------
  private boolean haySolapamiento(Reserva reserva) {
    return reservaRepository.existsReservaSolapada(
        reserva.getHotel(),
        reserva.getFechaInicio(),
        reserva.getFechaFin(),
        reserva.getId()
    );
  }

  private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) {
    if (fechaInicio.isAfter(fechaFin)) {
      throw new IllegalArgumentException();
    }
  }

  private Descuento crearDescuento(DescuentoDto dto) {

    return switch (dto.getTipo()) {

      case FIJO -> new DescuentoFijo(dto.getValor());

      case PORCENTAJE -> new DescuentoPorcentaje(dto.getPorcentaje());

      case POR_NOCHE -> new DescuentoPorNoche(dto.getCantMinima(), dto.getPorcentaje());
    };
  }


}
