package com.reserva.reserva_server.controller;

import com.reserva.reserva_server.dto.CrearReservaDto;
import com.reserva.reserva_server.dto.DescuentoDto;
import com.reserva.reserva_server.dto.ReservaDto;
import com.reserva.reserva_server.model.Reserva;
import com.reserva.reserva_server.model.enums.EstadoReserva;
import com.reserva.reserva_server.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

  private final ReservaService reservaService;

  public ReservaController(ReservaService reservaService) {
    this.reservaService = reservaService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReservaDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(this.toDto(reservaService.findById(id)));
  }

  @PostMapping("/")
  public ResponseEntity<ReservaDto> create(@RequestBody CrearReservaDto dto) {
    Reserva creada = reservaService.create(dto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(toDto(creada));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    reservaService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<ReservaDto> updateEstadoReserva(@PathVariable Long id,
                                                        @RequestBody EstadoReserva estadoReserva) {
    Reserva actualizada = reservaService.updateEstadoReserva(id, estadoReserva);
    return ResponseEntity.ok(this.toDto(actualizada));
  }

  @PatchMapping("/{id}/fechas")
  public ResponseEntity<ReservaDto> updateFechas(@PathVariable Long id,
                                                 @RequestParam LocalDate inicio,
                                                 @RequestParam LocalDate fin) {
    Reserva actualizada = reservaService.updateFechas(id, inicio, fin);
    return ResponseEntity.ok(this.toDto(actualizada));
  }

  @PatchMapping("/{id}/descuentos")
  public ResponseEntity<ReservaDto> agregarDescuentos(@PathVariable Long id,
                                                      @RequestBody List<DescuentoDto> descuentos) {
    Reserva reserva = reservaService.agregarDescuentos(id, descuentos);
    return ResponseEntity.ok(this.toDto(reserva));
  }
  /*
  @GetMapping
  public ResponseEntity<List<ReservaDto>> findAll() {
    List<Reserva> reservas = reservaService.findAll();
    List<ReservaDto> resultado = new ArrayList<>();
    for(Reserva reserva : reservas) {
      resultado.add(this.toDto(reserva));
    }
    return ResponseEntity.ok((resultado));
  }

   */

  @GetMapping("/fechas")
  public ResponseEntity<List<ReservaDto>> findAllOptionalFilters(
      @RequestParam(required = false) LocalDate fechaInicio,
      @RequestParam(required = false) LocalDate fechaFin
  ) {

    if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
      throw new IllegalArgumentException("fechaInicio no puede ser posterior a fechaFin");
    }
    List<Reserva> reservas = reservaService.findAll(fechaInicio, fechaFin);
    List<ReservaDto> resultado = new ArrayList<>();
    for(Reserva reserva : reservas) {
      resultado.add(this.toDto(reserva));
    }
    return ResponseEntity.ok((resultado));
  }


  private ReservaDto toDto(Reserva reserva) {
    return new ReservaDto(reserva.getUsuario(), reserva.getFechaInicio(),
        reserva.getFechaFin(), reserva.getHotel(), reserva.getEstadoReserva());
  }
}
